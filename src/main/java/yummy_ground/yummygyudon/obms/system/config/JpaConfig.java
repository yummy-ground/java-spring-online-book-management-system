package yummy_ground.yummygyudon.obms.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "yummy_ground.yummygyudon.obms.external.db.entity")
@EnableJpaRepositories(basePackages = "yummy_ground.yummygyudon.obms.external.db.repository")
public class JpaConfig {
}
