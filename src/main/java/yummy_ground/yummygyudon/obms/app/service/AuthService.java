package yummy_ground.yummygyudon.obms.app.service;

import yummy_ground.yummygyudon.obms.app.domain.AuthTokenPair;

public interface AuthService {
    AuthTokenPair authenticate(AuthEmailPasswordCommand command);

    AuthTokenPair refresh(AuthTokenRefreshCommand command);

    record AuthEmailPasswordCommand(
        String email,
        String password
    ){}
    record AuthTokenRefreshCommand(
            String refreshToken
    ){}
}
