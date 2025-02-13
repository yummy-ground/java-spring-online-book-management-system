package yummy_ground.yummygyudon.obms.system.config;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.customizers.OperationCustomizer;

import yummy_ground.yummygyudon.obms.system.value.DocsProperty;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private static final String JWT_NAME = "JWT";
    private static final String BEARER_NAME = "bearer";
    private static final String SWAGGER_CONTENT_MEDIA_TYPE = "application/json";
    private final DocsProperty docsProperty;

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            this.addResponseBodyWrapperSchema(operation, "200");
            this.addResponseBodyWrapperSchema(operation, "201");
            this.addResponseBodyWrapperSchema(operation, "400");
            this.addResponseBodyWrapperSchema(operation, "401");
            this.addResponseBodyWrapperSchema(operation, "403");
            this.addResponseBodyWrapperSchema(operation, "404");
            this.addResponseBodyWrapperSchema(operation, "500");
            return operation;
        };
    }

    private void addResponseBodyWrapperSchema(Operation operation, String statusStringValue) {
        ApiResponse targetResponse = operation.getResponses().getOrDefault(statusStringValue, null);
        if (targetResponse != null) {
            Content targetContent = targetResponse.getContent();
            if (is2xx(statusStringValue) && targetContent != null) {
                targetContent.forEach((key, mediaType) -> {
                    Schema<?> rawSchema = mediaType.getSchema();
                    mediaType.setSchema(wrapSuccessSchema(rawSchema));
                });
            } else {
                MediaType failMediaType = new MediaType();
                failMediaType.setSchema(wrapFailSchema());

                Content failContent = new Content().addMediaType(SWAGGER_CONTENT_MEDIA_TYPE, failMediaType);
                targetResponse.setContent(failContent);
            }
        }
    }

    private boolean is2xx(String statusStringValue) {
        return statusStringValue.startsWith("2");
    }

    private Schema<?> wrapSuccessSchema(Schema<?> rawSchema) {
        Schema<?> wrappedSchema = new Schema<>();

        wrappedSchema.addProperty("message", new Schema<>().type("string"));
        wrappedSchema.addProperty("data", rawSchema);

        return wrappedSchema;
    }

    private Schema<?> wrapFailSchema() {
        Schema<?> wrappedSchema = new Schema<>();
        wrappedSchema.addProperty("message", new Schema<>().type("string"));
        return wrappedSchema;
    }

    @Bean
    @Profile("local")
    public OpenAPI openAPIForLocal() {
        return new OpenAPI(SpecVersion.V30)
                .components(new Components())
                .info(apiInfo())
                .addSecurityItem(securityForJwt())
                .components(securityComponentForJwt())
                .servers(List.of(new Server().url(docsProperty.info().serverUrl())));
    }

    @Bean
    @Profile("dev")
    public OpenAPI openAPIForDevelopment() {
        return new OpenAPI(SpecVersion.V30)
                .components(new Components())
                .info(apiInfo())
                .addSecurityItem(securityForJwt())
                .components(securityComponentForJwt())
                .servers(List.of(new Server().url(docsProperty.info().serverUrl())));
    }

    Info apiInfo() {
        return new Info()
                .title(docsProperty.info().title())
                .description(docsProperty.info().description())
                .version(docsProperty.info().version());
    }

    private Components securityComponentForJwt() {
        return new Components()
                .addSecuritySchemes(JWT_NAME,
                        new SecurityScheme()
                                .name(JWT_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(BEARER_NAME)
                                .bearerFormat(JWT_NAME)
                );
    }

    private SecurityRequirement securityForJwt() {
        return new SecurityRequirement().addList(JWT_NAME);
    }

}
