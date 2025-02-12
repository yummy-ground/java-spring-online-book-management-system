package yummy_ground.yummygyudon.obms.app.domain;

import lombok.*;
import yummy_ground.yummygyudon.obms.support.code.error.UserError;
import yummy_ground.yummygyudon.obms.support.exception.UserException;

import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Role> roles;

    public static User of(String name, String email, String password, List<String> roleNames) {
        List<Role> roles = roleNames.stream().map(Role::findRoleByName).toList();
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .roles(roles)
                .build();
    }
    public static User of(long id, String name, String email, String password, List<String> roleNames) {
        List<Role> roles = roleNames.stream().map(Role::findRoleByName).toList();
        return User.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .roles(roles)
                .build();
    }

    @Getter
    @RequiredArgsConstructor(access = PRIVATE)
    public enum Role {
        USER("role_user"),
        ADMIN("role_admin"),
        ;
        private final String name;

        public static Role findRoleByName(String name) {
            return Arrays.stream(Role.values())
                    .filter(role -> role.getName().equals(name))
                    .findAny()
                    .orElseThrow(() -> new UserException(UserError.NOT_FOUND_ROLE));
        }
    }
}
