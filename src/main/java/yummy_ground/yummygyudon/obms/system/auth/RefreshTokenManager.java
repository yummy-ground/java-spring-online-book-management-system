package yummy_ground.yummygyudon.obms.system.auth;

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
public class RefreshTokenManager extends JwtManager<String> {
    private final JwtProperty jwtProperty;

    @Override
    public String generate(String data) {
        String issuer = jwtProperty.issuer().name();
        String key = jwtProperty.secret().refreshToken();
        long expiration = Long.parseLong(jwtProperty.expiration().refreshToken());
        return Jwts.builder()
                .setHeader(createHeader())
                .setSubject(data)
                .setIssuer(issuer)
                .setIssuedAt(createIssuedAt())
                .setExpiration(createExpiration(expiration))
                .signWith(createSigningKey(key), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String parse(String token) throws JwtException {
        String key = jwtProperty.secret().refreshToken();
        Claims tokenClaims = getClaimsFromToken(token, key);

        return tokenClaims.getSubject();
    }

}
