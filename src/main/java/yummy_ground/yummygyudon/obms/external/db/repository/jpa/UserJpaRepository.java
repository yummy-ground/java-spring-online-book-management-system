package yummy_ground.yummygyudon.obms.external.db.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import yummy_ground.yummygyudon.obms.external.db.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
