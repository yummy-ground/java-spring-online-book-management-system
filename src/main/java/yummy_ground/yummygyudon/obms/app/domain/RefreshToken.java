package yummy_ground.yummygyudon.obms.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

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
