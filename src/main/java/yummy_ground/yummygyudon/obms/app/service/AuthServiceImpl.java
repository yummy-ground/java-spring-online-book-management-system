package yummy_ground.yummygyudon.obms.app.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yummy_ground.yummygyudon.obms.app.domain.AccessToken;
import yummy_ground.yummygyudon.obms.app.domain.AuthTokenPair;
import yummy_ground.yummygyudon.obms.app.domain.RefreshToken;
import yummy_ground.yummygyudon.obms.app.domain.User;
import yummy_ground.yummygyudon.obms.app.repository.RefreshTokenRepository;
import yummy_ground.yummygyudon.obms.app.repository.UserRepository;
import yummy_ground.yummygyudon.obms.support.code.error.AuthError;
import yummy_ground.yummygyudon.obms.support.code.error.GlobalError;
import yummy_ground.yummygyudon.obms.support.exception.AuthException;
import yummy_ground.yummygyudon.obms.support.exception.GlobalException;
import yummy_ground.yummygyudon.obms.system.auth.AccessTokenManager;
import yummy_ground.yummygyudon.obms.system.auth.CustomUserAuthentication;
import yummy_ground.yummygyudon.obms.system.auth.RefreshTokenManager;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @Override
    public AuthTokenPair authenticate(AuthEmailPasswordCommand command) {
        User targetUser = userRepository.findByEmail(command.email());
        verifyPassword(command.password(), targetUser.getPassword());

        String accessTokenValue = generateAccessToken(targetUser.getId());
        AccessToken accessToken = AccessToken.of(accessTokenValue);

        String refreshTokenValue = generateRefreshToken();
        RefreshToken refreshToken = RefreshToken.of(refreshTokenValue, targetUser.getId());
        RefreshToken savedToken = refreshTokenRepository.save(refreshToken);
        return AuthTokenPair.of(accessToken, savedToken);
    }

    @Override
    public AuthTokenPair refresh(AuthTokenRefreshCommand command) {
        RefreshToken existRefreshToken = refreshTokenRepository.findByToken(command.refreshToken());

        verifyRefreshToken(existRefreshToken);

        String accessTokenValue = generateAccessToken(existRefreshToken.getUserId());
        AccessToken accessToken = AccessToken.of(accessTokenValue);

        String refreshTokenValue = generateRefreshToken();
        RefreshToken refreshToken = RefreshToken.of(refreshTokenValue);
        RefreshToken savedToken = refreshTokenRepository.save(refreshToken);
        return AuthTokenPair.of(accessToken, savedToken);
    }

    private void verifyPassword(String rawPassword, String encodedPassword) {
        boolean isMatched = passwordEncoder.matches(rawPassword, encodedPassword);
        if (!isMatched) {
            throw new AuthException(AuthError.INVALID_PASSWORD);
        }
    }

    private void verifyRefreshToken(RefreshToken refreshToken) {
        try {
            refreshTokenManager.parse(refreshToken.getToken());
        } catch (ExpiredJwtException e) {
            throw new AuthException(AuthError.EXPIRED_REFRESH_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new AuthException(AuthError.UNSUPPORTED_FORMAT_REFRESH_TOKEN);
        } catch (MalformedJwtException e) {
            throw new AuthException(AuthError.INVALID_FORMAT_REFRESH_TOKEN);
        } catch (SignatureException e) {
            throw new AuthException(AuthError.INVALID_SIGNATURE_REFRESH_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new AuthException(AuthError.EMPTY_REFRESH_TOKEN);
        } catch (JwtException e) {
            throw new GlobalException(GlobalError.INTERNAL_SERVER_ERROR_IN_RUNTIME);
        } finally {
            refreshTokenRepository.deleteRefreshToken(refreshToken);
        }
    }

    private String generateAccessToken(long userId) {
        CustomUserAuthentication userAuthentication = new CustomUserAuthentication(userId, null, null);
        return accessTokenManager.generate(userAuthentication);
    }
    private String generateRefreshToken() {
        String uniqueData = UUID.randomUUID().toString();
        return refreshTokenManager.generate(uniqueData);
    }

}
