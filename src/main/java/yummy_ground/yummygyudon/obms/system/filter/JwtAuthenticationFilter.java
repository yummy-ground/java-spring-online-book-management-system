package yummy_ground.yummygyudon.obms.system.filter;

import java.util.List;
import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

import yummy_ground.yummygyudon.obms.support.code.error.AuthError;
import yummy_ground.yummygyudon.obms.support.code.error.GlobalError;
import yummy_ground.yummygyudon.obms.support.exception.AuthException;
import yummy_ground.yummygyudon.obms.support.exception.GlobalException;
import yummy_ground.yummygyudon.obms.system.auth.AccessTokenManager;
import yummy_ground.yummygyudon.obms.system.auth.CustomUserAuthentication;

import static yummy_ground.yummygyudon.obms.support.constant.RequestConstant.ACTUATOR_URI_PREFIX;
import static yummy_ground.yummygyudon.obms.support.constant.RequestConstant.AUTH_URI_PREFIX;
import static yummy_ground.yummygyudon.obms.support.constant.RequestConstant.OPEN_API_V3_URI_PREFIX;
import static yummy_ground.yummygyudon.obms.support.constant.RequestConstant.REISSUE_URI_PREFIX;
import static yummy_ground.yummygyudon.obms.support.constant.RequestConstant.SWAGGER_URI_PREFIX;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String HEADER_NAME_AUTHORIZATION = "Authorization";
    private static final List<String> WHITE_URIS = List.of(
            ACTUATOR_URI_PREFIX, AUTH_URI_PREFIX, REISSUE_URI_PREFIX, OPEN_API_V3_URI_PREFIX, SWAGGER_URI_PREFIX
    );
    private static final String BEARER_PREFIX = "Bearer ";
    private final AccessTokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String accessToken = extractAccessToken(request);
            CustomUserAuthentication authentication = tokenManager.parse(accessToken);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (ClassCastException | RequiredTypeException e) {
            throw new AuthException(AuthError.UNINTENDED_PRINCIPAL);
        } catch (ExpiredJwtException e) {
            throw new AuthException(AuthError.EXPIRED_ACCESS_TOKEN);
        } catch (UnsupportedJwtException e){
            throw new AuthException(AuthError.UNSUPPORTED_FORMAT_ACCESS_TOKEN);
        } catch (MalformedJwtException e){
            throw new AuthException(AuthError.INVALID_FORMAT_ACCESS_TOKEN);
        } catch (SignatureException e) {
            throw new AuthException(AuthError.INVALID_SIGNATURE_ACCESS_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new AuthException(AuthError.EMPTY_ACCESS_TOKEN);
        } catch (ServletException | IOException exception) {
            throw new GlobalException(GlobalError.INTERNAL_SERVER_ERROR_IN_FILTER);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        return WHITE_URIS.stream().anyMatch(uri -> request.getRequestURI().contains(uri));
    }

    private String extractAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(HEADER_NAME_AUTHORIZATION);
        if (authorization == null) {
            throw new AuthException(AuthError.NO_ACCESS_TOKEN_IN_HEADER);
        }
        String tokenValue = authorization.substring(BEARER_PREFIX.length());
        if (!StringUtils.hasText(tokenValue)) {
            throw new AuthException(AuthError.NO_ACCESS_TOKEN_IN_HEADER);
        }
        return tokenValue;
    }

}
