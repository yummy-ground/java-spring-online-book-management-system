package yummy_ground.yummygyudon.obms.system.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomUserAuthentication extends UsernamePasswordAuthenticationToken {
    public CustomUserAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
