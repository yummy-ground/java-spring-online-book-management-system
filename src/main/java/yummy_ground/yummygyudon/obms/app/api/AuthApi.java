package yummy_ground.yummygyudon.obms.app.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.app.api.dto.request.AuthRequest;
import yummy_ground.yummygyudon.obms.app.api.dto.response.AuthResponse;

@Tag(
        name = "사용자 인증 API",
        description = """
                사용자의 온라인 도서 관리 시스템 이용을 위한 인증 API입니다.<br/>
                * 본 API는 인증되지 않은 상태에서 사용 가능합니다.
                """
)
public interface AuthApi {

    @Operation(
            summary = "신규 인증 및 AccessToken 발급",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = AuthRequest.AuthenticationInfo.class))
            )
    )
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "발급 성공",
                    content = @Content(schema = @Schema(contentMediaType = "application/json", implementation = AuthResponse.Authorization.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "올바르지 않은 Request Body 요청값"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "비밀번호 불일치"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않는 사용자"
            )
    })
    ResponseEntity<BaseResponse<?>> auth(AuthRequest.AuthenticationInfo authentication, @Schema(hidden = true) HttpServletResponse response);


    @Operation(
            summary = "AccessToken 재발급 (using RefreshToken)"
    )
    @SecurityRequirements(value = {})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "재발급 성공",
                    content = @Content(schema = @Schema(contentMediaType = "application/json", implementation = AuthResponse.Authorization.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "존재하지 않는 Refresh Token Cookie"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "유효하지 않거나 만료된 Refresh Token / 등록되지 않은 Refresh Token"
            )
    })
    ResponseEntity<BaseResponse<?>> refresh(String refreshToken, @Schema(hidden = true) HttpServletResponse response);
}
