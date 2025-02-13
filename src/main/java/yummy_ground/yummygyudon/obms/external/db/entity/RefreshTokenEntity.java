package yummy_ground.yummygyudon.obms.external.db.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import yummy_ground.yummygyudon.obms.app.domain.RefreshToken;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(schema = "book_management_system", name = "refresh_tokens")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class RefreshTokenEntity extends BaseEntity {

    @Column(name = "user_id", unique = true, nullable = false)
    private long userId;
    @Column(name = "token", unique = true, nullable = false, columnDefinition = "text")
    private String token;

    public RefreshToken toDomain() {
        return RefreshToken.of(this.token, this.userId);
    }

    public static RefreshTokenEntity from(RefreshToken refreshToken) {
        return RefreshTokenEntity.builder()
                .userId(refreshToken.getUserId())
                .token(refreshToken.getToken())
                .build();
    }

}
