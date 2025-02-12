package yummy_ground.yummygyudon.obms.app.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class RentalUserInfo {

    private long userId;
    private String userName;

    public static RentalUserInfo of(long id) {
        return RentalUserInfo.builder()
                .userId(id)
                .build();
    }

    public static RentalUserInfo of(long id, String name) {
        return RentalUserInfo.builder()
                .userId(id)
                .userName(name)
                .build();
    }
}
