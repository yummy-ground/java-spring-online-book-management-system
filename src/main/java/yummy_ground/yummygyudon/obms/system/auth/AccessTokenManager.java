package yummy_ground.yummygyudon.obms.system.auth;

import java.io.IOException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import yummy_ground.yummygyudon.obms.system.jwt.JwtManager;
import yummy_ground.yummygyudon.obms.system.value.JwtProperty;

@Component
@RequiredArgsConstructor
public class AccessTokenManager extends JwtManager<CustomUserAuthentication> {
    private static final String CLAIM_KEY_USER_ID = "user_id";
    private final JwtProperty jwtProperty;

    @Override
    public String generate(CustomUserAuthentication authentication) {
        String issuer = jwtProperty.issuer().name();
        String key = jwtProperty.secret().accessToken();
        long expiration = Long.parseLong(jwtProperty.expiration().accessToken());
        return Jwts.builder()
                .setHeader(createHeader())
                .claim(CLAIM_KEY_USER_ID, authentication.getPrincipal())
                .setIssuer(issuer)
                .setIssuedAt(createIssuedAt())
                .setExpiration(createExpiration(expiration))
                .signWith(createSigningKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public CustomUserAuthentication parse(String token) throws JwtException, IOException {
        String key = jwtProperty.secret().accessToken();
        Claims claims = getClaimsFromToken(token, key);

        Long userId = claims.get(CLAIM_KEY_USER_ID, Long.class);
        return new CustomUserAuthentication(userId, null, null);
    }

}
