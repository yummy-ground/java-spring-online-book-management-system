package yummy_ground.yummygyudon.obms.app.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class UserResponse {
    @Schema(title = "사용자 목록 응답")
    public record All(
            @ArraySchema(schema = @Schema(implementation = Detail.class))
            @JsonProperty("users")
            List<Detail> users
    ){}

    @Schema(title = "사용자 상세 정보 응답")
    public record Detail(
            @Schema(description = "사용자 ID")
            @JsonProperty("user_id")
            long id,
            @Schema(description = "사용자 이름")
            @JsonProperty("user_name")
            String name,
            @Schema(description = "사용자 이메일", example = "example@exp.com")
            @JsonProperty("user_email")
            String email,
            @Schema(description = "사용자 권한", example = "['user', 'admin']")
            @JsonProperty("user_roles")
            List<String> roles
    ){}

}
