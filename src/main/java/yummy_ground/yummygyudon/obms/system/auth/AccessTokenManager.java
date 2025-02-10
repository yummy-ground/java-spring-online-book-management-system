package yummy_ground.yummygyudon.obms.system.auth;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yummy_ground.yummygyudon.obms.system.jwt.JwtManager;
import yummy_ground.yummygyudon.obms.system.value.JwtProperty;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccessTokenManager extends JwtManager<CustomUserAuthentication> {
    private static final String CLAIM_KEY_USER_ID = "user_id";
    private final JwtProperty jwtProperty;

    @Override
    public String generate(CustomUserAuthentication authentication) {
        String subject = UUID.randomUUID().toString();
        String issuer = jwtProperty.issuer().name();
        String key = jwtProperty.secret().accessToken();
        long expiration = Long.parseLong(jwtProperty.expiration().accessToken());
        return Jwts.builder()
                .setHeader(createHeader())
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(createIssuedAt())
                .setExpiration(createExpiration(expiration))
                .signWith(createSigningKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public CustomUserAuthentication parse(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, ClassCastException {
        String key = jwtProperty.secret().accessToken();
        Claims claims = getClaimsFromToken(token, key);

        long userId = claims.get(CLAIM_KEY_USER_ID, Long.class);
        return new CustomUserAuthentication(userId, null);
    }

}
