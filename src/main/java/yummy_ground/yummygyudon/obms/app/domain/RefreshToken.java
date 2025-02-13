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
public class RefreshToken {
    private long userId;
    private String token;

    public static RefreshToken of(String token) {
        return RefreshToken.builder()
                .token(token)
                .build();
    }
    public static RefreshToken of(String token, long userId) {
        return RefreshToken.builder()
                .token(token)
                .userId(userId)
                .build();
    }
}
