package yummy_ground.yummygyudon.obms.app.api.dto.response;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import yummy_ground.yummygyudon.obms.app.domain.AuthTokenPair;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class AuthResponse {

    @Schema(title = "인증 완료 및 인가 응답")
    public record Authorization(
            @Schema(description = "인증 토큰")
            @JsonProperty("access_token")
            String accessToken,
            @Schema(description = "토큰 타입", example = "Bearer")
            @JsonProperty(value = "token_type", defaultValue = "Bearer")
            String tokenType
    ){
        public static Authorization from(AuthTokenPair tokenPair) {
            return new Authorization(
                    tokenPair.getAccessToken().getToken(),
                    tokenPair.getAccessToken().getType()
            );
        }
    }

}
