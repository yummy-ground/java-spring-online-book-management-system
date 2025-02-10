package yummy_ground.yummygyudon.obms.system.jwt;

import java.util.Map;
import java.util.Date;
import java.time.ZoneId;
import java.util.Base64;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;

import yummy_ground.yummygyudon.obms.support.constant.JwtConstant;

public abstract class JwtManager<T> {
    private static final String HEADER_KEY_ALGORITHM = "alg";
    private static final String HEADER_KEY_TYPE = "typ";

    protected Map<String, Object> createHeader() {
        return Map.of(
                HEADER_KEY_TYPE, JwtConstant.TYPE,
                HEADER_KEY_ALGORITHM, JwtConstant.ALGORITHM
        );
    }
    protected Date createIssuedAt() {
        LocalDateTime issuedDateTime = LocalDateTime.now();
        return Date.from(issuedDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    protected Date createExpiration(long expiration) {
        LocalDateTime expiredDateTime = LocalDateTime.now().plusSeconds(expiration);
        return Date.from(expiredDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    protected Key createSigningKey(String originKey) {
        String encodedKey = Base64.getEncoder().encodeToString(originKey.getBytes(StandardCharsets.UTF_8));
        byte[] encodedKeyBytes = DatatypeConverter.parseBase64Binary(encodedKey);

        return new SecretKeySpec(encodedKeyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    protected Claims getClaimsFromToken(String token, String key) throws JwtException {
        Key signingKey = createSigningKey(key);
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    protected abstract String generate(T data);

    protected abstract T parse(String token) throws IOException, JwtException;

}
