package yummy_ground.yummygyudon.obms.system.config.init;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Profile("local")
@Component
@RequiredArgsConstructor
public class DataInitRunner implements ApplicationRunner {
    private static final String LINE = "========================================";
    private static final String INSERT_USER_QUERY_FORMAT = "INSERT INTO book_management_system.users (name, email, password, roles, created_at, updated_at) VALUES('%s', '%s', '%s', '%s', now(), now())";

    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        log.info(LINE);
        initDataForAdmin("admin", "admin@abc.com", "admin_password");
        log.info(LINE);
        initDataForUser("userA", "user1@abc.com", "user1_password");
        log.info(LINE);
    }
    private void initDataForAdmin(String name, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        log.info("> Admin (ID : 1) - ID : {}", email);
        log.info("> Anmin (ID : 1) - Password : {} ({})", password, encodedPassword);
        jdbcTemplate.update(String.format(INSERT_USER_QUERY_FORMAT, name, email, encodedPassword, "[\"role_user\", \"role_admin\"]"));
    }
    private void initDataForUser(String name, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        log.info("> User (ID : 2) - ID : {}", email);
        log.info("> User (ID : 2) - Password : {} ({})", password, passwordEncoder.encode(password));
        jdbcTemplate.update(String.format(INSERT_USER_QUERY_FORMAT, name, email, encodedPassword, "[\"role_user\"]"));
    }

}
