package yummy_ground.yummygyudon.obms.app.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class UserRequest {

        @Schema(title = "사용자 등록 요청")
        public record Register(
            @Schema(description = "사용자 이름", example = "user")
            @NotBlank(message = "이름을 입력해야 합니다.")
            @JsonProperty("user_name")
            String name,
            @Schema(description = "사용자 이메일", example = "user@abc.com", pattern = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$")
            @Email(message = "이메일 형식이 맞지 않습니다.")
            @NotBlank(message = "이메일을 입력해야 합니다.")
            @JsonProperty("user_email")
            String email,
            @Schema(description = "사용자 비밀번호", example = "UserPassword123!")
            @NotBlank(message = "비밀번호를 입력해야 합니다.")
            @JsonProperty("user_password")
            String password
        ){}

}
