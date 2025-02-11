package yummy_ground.yummygyudon.obms.support.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Hidden
@Builder(access = PRIVATE)
public record BaseResponse<T> (
        String message,
        @JsonInclude(value = NON_NULL)
        T data
) {
    public static BaseResponse<?> of(String message) {
        return BaseResponse.builder()
                .message(message)
                .build();
    }

    public static <T> BaseResponse<?> of(String message, T data) {
        return BaseResponse.builder()
                .message(message)
                .data(data)
                .build();
    }

}