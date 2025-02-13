package yummy_ground.yummygyudon.obms.app.api.dto.request;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import yummy_ground.yummygyudon.obms.app.service.AuthService;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class AuthRequest {

        @Schema(title = "인증 요청")
        public record AuthenticationInfo(
                @Schema(description = "사용자 이메일", example = "user@abc.com")
                @Email(message = "이메일 형식이 맞지 않습니다.")
                @NotBlank(message = "이메일을 입력해야 합니다.")
                @JsonProperty(value = "user_email", defaultValue = "admin@abc.com")
                String email,
                @Schema(description = "사용자 비밀번호", example = "UserPassword123!")
                @NotBlank(message = "비밀번호를 입력해야 합니다.")
                @JsonProperty(value = "user_password", defaultValue = "admin_password")
                String password
        ){
            public AuthService.AuthEmailPasswordCommand toCommand() {
                return new AuthService.AuthEmailPasswordCommand(this.email, this.password);
            }
        }

}
