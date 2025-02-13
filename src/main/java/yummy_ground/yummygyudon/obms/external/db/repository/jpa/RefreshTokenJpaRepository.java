package yummy_ground.yummygyudon.obms.external.db.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import yummy_ground.yummygyudon.obms.external.db.entity.RefreshTokenEntity;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    void deleteByUserId(long userId);
}
