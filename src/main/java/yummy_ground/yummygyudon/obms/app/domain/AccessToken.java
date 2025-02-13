package yummy_ground.yummygyudon.obms.app.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class AccessToken {
    private final String type = "Bearer";
    private String token;

    public static AccessToken of(String token) {
        return AccessToken.builder()
                .token(token)
                .build();
    }
}
