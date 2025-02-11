package yummy_ground.yummygyudon.obms.system.config;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import yummy_ground.yummygyudon.obms.system.value.DocsProperty;
import yummy_ground.yummygyudon.obms.system.value.JwtProperty;
import yummy_ground.yummygyudon.obms.system.resolver.UserIdentityArgumentResolver;

@Configuration(value = "CustomApplicationConfig")
@ConfigurationPropertiesScan(basePackages = "yummy_ground.yummygyudon.obms.system.value")
@EnableConfigurationProperties({
        JwtProperty.class,
        DocsProperty.class
})
@RequiredArgsConstructor
public class ApplicationConfig implements WebMvcConfigurer {
    private final UserIdentityArgumentResolver userIdentityArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userIdentityArgumentResolver);
    }

}
