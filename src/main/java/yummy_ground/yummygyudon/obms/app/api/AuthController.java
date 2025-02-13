package yummy_ground.yummygyudon.obms.app.api;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yummy_ground.yummygyudon.obms.app.api.dto.request.AuthRequest;
import yummy_ground.yummygyudon.obms.app.api.dto.response.AuthResponse;
import yummy_ground.yummygyudon.obms.app.domain.AuthTokenPair;
import yummy_ground.yummygyudon.obms.app.domain.RefreshToken;
import yummy_ground.yummygyudon.obms.app.service.AuthService;
import yummy_ground.yummygyudon.obms.support.code.success.AuthSuccess;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.support.util.ApiResponseUtil;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private static final String COOKIE_NAME_REFRESH_TOKEN = "refresh_token";
    private final AuthService authService;

    @Override
    @PostMapping
    public ResponseEntity<BaseResponse<?>> auth(
            @RequestBody @Valid AuthRequest.AuthenticationInfo newAuthentication,
            HttpServletResponse response
    ) {
        System.out.println(newAuthentication.toString());
        AuthTokenPair tokenPair = authService.authenticate(newAuthentication.toCommand());

        setRefreshTokenCookie(response, tokenPair.getRefreshToken());
        AuthResponse.Authorization result = AuthResponse.Authorization.from(tokenPair);
        return ApiResponseUtil.successContent(AuthSuccess.AUTHORIZE, result);
    }

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<BaseResponse<?>> refresh(
            @CookieValue(COOKIE_NAME_REFRESH_TOKEN) String refreshToken,
            HttpServletResponse response
    ) {
        AuthService.AuthTokenRefreshCommand refreshCommand = new AuthService.AuthTokenRefreshCommand(refreshToken);
        AuthTokenPair tokenPair = authService.refresh(refreshCommand);

        setRefreshTokenCookie(response, tokenPair.getRefreshToken());
        AuthResponse.Authorization result = AuthResponse.Authorization.from(tokenPair);
        return ApiResponseUtil.successContent(AuthSuccess.REISSUE, result);
    }

    private void setRefreshTokenCookie(HttpServletResponse response, RefreshToken refreshToken) {
        ResponseCookie responseCookie = ResponseCookie.from(COOKIE_NAME_REFRESH_TOKEN, refreshToken.getToken())
                .maxAge(86400)
                .path("/")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .build();
        response.addHeader("Set-Cookie", responseCookie.toString());
    }
}
