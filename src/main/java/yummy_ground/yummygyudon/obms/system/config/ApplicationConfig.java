package yummy_ground.yummygyudon.obms.system.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import yummy_ground.yummygyudon.obms.system.value.JwtProperty;

@Configuration(value = "CustomApplicationConfig")
@ConfigurationPropertiesScan(basePackages = "yummy_ground.yummygyudon.obms.system.value")
@EnableConfigurationProperties({
        JwtProperty.class
})
public class ApplicationConfig {
}
