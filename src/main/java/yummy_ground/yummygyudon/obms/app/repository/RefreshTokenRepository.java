package yummy_ground.yummygyudon.obms.app.repository;

import yummy_ground.yummygyudon.obms.app.domain.RefreshToken;

public interface RefreshTokenRepository {
    RefreshToken save(RefreshToken refreshToken);
    RefreshToken findByToken(String token);

    void deleteRefreshToken(RefreshToken refreshToken);
}
