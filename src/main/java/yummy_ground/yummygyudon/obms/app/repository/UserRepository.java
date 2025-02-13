package yummy_ground.yummygyudon.obms.app.repository;

import java.util.List;

import yummy_ground.yummygyudon.obms.app.domain.User;


public interface UserRepository {
    User save(User user);
    User findById(long id);
    User findByEmail(String email);
    List<User> findAll();
}
