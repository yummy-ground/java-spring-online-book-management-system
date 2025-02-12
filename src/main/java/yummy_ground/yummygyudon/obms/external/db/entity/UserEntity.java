package yummy_ground.yummygyudon.obms.external.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

import yummy_ground.yummygyudon.obms.app.domain.User;
import yummy_ground.yummygyudon.obms.support.db.StringListConverter;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity(name = "user")
@Table(schema = "book_management_system", name = "users")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class UserEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "roles", nullable = false)
    @Convert(converter = StringListConverter.class)
    private List<String> roles;

    @Builder(access = PRIVATE)
    public UserEntity(Long id, String name, String email, String password, List<String> roles) {
        setId(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User toDomain() {
        return User.of(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getPassword(),
                this.getRoles()
        );
    }

    public static UserEntity fromDomain(User user) {
        List<String> roleNames = user.getRoles().stream().map(User.Role::getName).toList();
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(roleNames)
                .build();
    }


}
