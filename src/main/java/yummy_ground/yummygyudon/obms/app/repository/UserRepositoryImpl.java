package yummy_ground.yummygyudon.obms.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yummy_ground.yummygyudon.obms.app.domain.User;
import yummy_ground.yummygyudon.obms.external.db.entity.UserEntity;
import yummy_ground.yummygyudon.obms.external.db.repository.jpa.UserJpaRepository;
import yummy_ground.yummygyudon.obms.support.code.error.UserError;
import yummy_ground.yummygyudon.obms.support.exception.UserException;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);
        UserEntity savedUserEntity = jpaRepository.save(userEntity);
        return savedUserEntity.toDomain();
    }

    @Override
    public User findById(long id) {
        UserEntity findUserEntity = jpaRepository.findById(id)
                .orElseThrow(() -> new UserException(UserError.NOT_FOUND_USER));

        return findUserEntity.toDomain();
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(UserEntity::toDomain)
                .toList();
    }

}
