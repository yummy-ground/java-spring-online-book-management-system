package yummy_ground.yummygyudon.obms.app.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yummy_ground.yummygyudon.obms.app.api.dto.request.UserRequest;
import yummy_ground.yummygyudon.obms.app.api.dto.response.UserResponse;
import yummy_ground.yummygyudon.obms.app.domain.User;
import yummy_ground.yummygyudon.obms.app.service.UserService;
import yummy_ground.yummygyudon.obms.support.code.success.UserSuccess;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.support.util.ApiResponseUtil;
import yummy_ground.yummygyudon.obms.system.resolver.UserId;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<BaseResponse<?>> register(
            @RequestBody @Valid UserRequest.UserRegister newUser
    ) {
        UserService.RegisterCommand userRegisterCommand = newUser.toCommand();
        User savedUser = userService.registerUser(userRegisterCommand);
        UserResponse.UserDetail result = UserResponse.UserDetail.from(savedUser);
        return ApiResponseUtil.successContent(UserSuccess.REGISTER_USER, result);
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<?>> getDetail(
            @PathVariable("userId") long userId
    ) {
        System.out.println(userId);
        User user = userService.findUser(userId);
        UserResponse.UserDetail result = UserResponse.UserDetail.from(user);
        return ApiResponseUtil.successContent(UserSuccess.GET_USER, result);
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<BaseResponse<?>> getMyDetail(
            @UserId long userId
    ) {
        User user = userService.findUser(userId);
        UserResponse.UserDetail result = UserResponse.UserDetail.from(user);
        return ApiResponseUtil.successContent(UserSuccess.GET_USER, result);
    }

    @Override
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAll() {
        List<User> users = userService.findUsers();
        UserResponse.UserAll result = UserResponse.UserAll.from(users);
        return ApiResponseUtil.successContent(UserSuccess.GET_USERS, result);
    }

}
