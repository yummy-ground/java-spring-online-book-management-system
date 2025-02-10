package yummy_ground.yummygyudon.obms.system.value;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperty(
        Secret secret,
        Expiration expiration,
        Issuer issuer

) {
    public record Secret(
            String accessToken,
            String refreshToken
    ) {}

    public record Expiration(
            String accessToken,
            String refreshToken
    ) {}

    public record Issuer(
            String name
    ){}
}
