package yummy_ground.yummygyudon.obms.app.service;

import yummy_ground.yummygyudon.obms.app.domain.User;

import java.util.List;

public interface UserService {
    User registerUser(RegisterCommand command);
    User findUser(long id);

    List<User> findUsers();

    record RegisterCommand(
            String name,
            String email,
            String password,
            List<String> roleNames
    ) { }
}
