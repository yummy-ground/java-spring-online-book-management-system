package yummy_ground.yummygyudon.obms.app.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class AuthTokenPair {
    private AccessToken accessToken;
    private RefreshToken refreshToken;

    public static AuthTokenPair of(AccessToken accessToken, RefreshToken refreshToken) {
        return AuthTokenPair.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
