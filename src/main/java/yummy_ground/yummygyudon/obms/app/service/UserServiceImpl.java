package yummy_ground.yummygyudon.obms.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yummy_ground.yummygyudon.obms.app.domain.User;
import yummy_ground.yummygyudon.obms.app.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterCommand command) {
        String encodedPassword = passwordEncoder.encode(command.password());
        User registerUser = User.of(command.name(), command.email(), encodedPassword, command.roleNames());
        return userRepository.save(registerUser);
    }

    @Override
    public User findUser(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }
}
