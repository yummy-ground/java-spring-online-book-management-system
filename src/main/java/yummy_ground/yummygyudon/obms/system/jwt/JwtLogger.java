package yummy_ground.yummygyudon.obms.system.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import yummy_ground.yummygyudon.obms.system.auth.AccessTokenManager;
import yummy_ground.yummygyudon.obms.system.auth.CustomUserAuthentication;
import yummy_ground.yummygyudon.obms.system.auth.RefreshTokenManager;

import java.util.UUID;

@Slf4j
@Profile("local")
@Component
@RequiredArgsConstructor
public class JwtLogger implements ApplicationRunner {
    private static final String LINE = "========================================";
    private static final String ADMIN_EMAIL = "admin@abc.com";
    private static final String USER_EMAIL = "user1@abc.com";
    private static final String ADMIN_RAW_PASSWORD = "admin_password";
    private static final String USER_RAW_PASSWORD = "user1_password";
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        log.info(LINE);
        showLogForAdmin();
        log.info(LINE);
        showLogForUser();
        log.info(LINE);
    }
    private void showLogForAdmin() {
        String accessTokenForAdmin = accessTokenManager.generate(new CustomUserAuthentication(1, null));
        String refreshTokenForAdmin = refreshTokenManager.generate(UUID.randomUUID().toString());
        log.info("> Admin (ID : 1) - Access Token : {}", accessTokenForAdmin);
        log.info("> Admin (ID : 1) - Refresh Token : {}", refreshTokenForAdmin);
        log.info("> Admin (ID : 1) - ID : {}", ADMIN_EMAIL);
        log.info("> Anmin (ID : 1) - Password : {} ({})", ADMIN_RAW_PASSWORD, passwordEncoder.encode(ADMIN_RAW_PASSWORD));
    }
    private void showLogForUser() {
        String accessTokenForUser = accessTokenManager.generate(new CustomUserAuthentication(2, null));
        String refreshTokenForUser = refreshTokenManager.generate(UUID.randomUUID().toString());
        log.info("> User (ID : 2) - Access Token : {}", accessTokenForUser);
        log.info("> User (ID : 2) - Refresh Token : {}", refreshTokenForUser);
        log.info("> User (ID : 2) - ID : {}", USER_EMAIL);
        log.info("> User (ID : 2) - Password : {} ({})", USER_RAW_PASSWORD, passwordEncoder.encode(USER_RAW_PASSWORD));
    }
}
