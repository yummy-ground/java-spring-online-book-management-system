package yummy_ground.yummygyudon.obms.app.repository;

import yummy_ground.yummygyudon.obms.app.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User findById(long id);
    List<User> findAll();
}
