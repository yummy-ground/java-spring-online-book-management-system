package yummy_ground.yummygyudon.obms.app.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yummy_ground.yummygyudon.obms.app.domain.RefreshToken;
import yummy_ground.yummygyudon.obms.external.db.entity.RefreshTokenEntity;
import yummy_ground.yummygyudon.obms.external.db.repository.jpa.RefreshTokenJpaRepository;
import yummy_ground.yummygyudon.obms.support.code.error.AuthError;
import yummy_ground.yummygyudon.obms.support.exception.AuthException;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository jpaRepository;

    @Override
    @Transactional
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity newToken = RefreshTokenEntity.from(refreshToken);
        RefreshTokenEntity savedToken = jpaRepository.save(newToken);
        return savedToken.toDomain();
    }

    @Override
    public RefreshToken findByToken(String token) {
        RefreshTokenEntity refreshTokenEntity = jpaRepository.findByToken(token)
                .orElseThrow(() -> new AuthException(AuthError.NO_REFRESH_TOKEN));
        return refreshTokenEntity.toDomain();
    }

    @Override
    @Transactional
    public void deleteRefreshToken(RefreshToken refreshToken) {
        jpaRepository.deleteByUserId(refreshToken.getUserId());
    }
}
