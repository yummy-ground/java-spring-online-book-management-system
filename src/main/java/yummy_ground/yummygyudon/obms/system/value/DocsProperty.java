package yummy_ground.yummygyudon.obms.system.value;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "springdoc")
public record DocsProperty (
        Info info,
        ApiDocs apiDocs,
        SwaggerUI swaggerUI
) {
    @ConfigurationProperties(prefix = "info")
    public record Info(
            String version,
            String title,
            String description
    ) {}

    @ConfigurationProperties(prefix = "api-docs")
    public record ApiDocs(
            String version
    ) {}
    @ConfigurationProperties(prefix = "swagger-ui")
    public record SwaggerUI(

    ) {}
}
