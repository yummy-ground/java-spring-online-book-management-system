package yummy_ground.yummygyudon.obms.app.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import yummy_ground.yummygyudon.obms.app.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class UserRequest {

        @Schema(title = "사용자 등록 요청")
        public record UserRegister(
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
            String password,

            @Schema(description = "관리자 여부", example = "true", defaultValue = "false")
            @JsonProperty(value = "user_is_admin", defaultValue = "false")
            boolean isAdmin
        ){
            private static final String USER_ROLE_NAME = "role_user";
            private static final String ADMIN_ROLE_NAME = "role_admin";
            public UserService.RegisterCommand toCommand() {
                List<String> roles = new ArrayList<>();
                roles.add(USER_ROLE_NAME);
                if (this.isAdmin) {
                    roles.add(ADMIN_ROLE_NAME);
                }
                return new UserService.RegisterCommand(
                        this.name,
                        this.email,
                        this.password,
                        roles
                );
            }
        }

}
