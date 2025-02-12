package yummy_ground.yummygyudon.obms.app.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import yummy_ground.yummygyudon.obms.app.domain.User;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class UserResponse {
    @Schema(title = "사용자 목록 응답")
    @Builder(access = PRIVATE)
    public record UserAll(
            @Schema(description = "사용자 목록")
            @JsonProperty("users")
            List<UserDetail> users
    ){
        public static UserAll from(List<User> users) {
            List<UserDetail> details = users.stream().map(UserDetail::from).toList();
            return UserAll.builder()
                    .users(details)
                    .build();
        }
    }

    @Schema(title = "사용자 상세 정보 응답")
    @Builder(access = PRIVATE)
    public record UserDetail(
            @Schema(description = "사용자 ID")
            @JsonProperty("user_id")
            long id,
            @Schema(description = "사용자 이름")
            @JsonProperty("user_name")
            String name,
            @Schema(description = "사용자 이메일", example = "example@exp.com")
            @JsonProperty("user_email")
            String email,
            @ArraySchema(schema = @Schema(description = "사용자 권한"))
            @JsonProperty("user_roles")
            List<String> roles
    ){
        public static UserDetail from(User user) {
            List<String> roleNames = user.getRoles().stream().map(User.Role::getName).toList();
            return UserDetail.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .roles(roleNames)
                    .build();
        }
    }

}
