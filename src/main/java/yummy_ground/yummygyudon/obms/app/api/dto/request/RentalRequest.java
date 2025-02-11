package yummy_ground.yummygyudon.obms.app.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class RentalRequest {

    @Schema(title = "대여 등록 요청")
    public record Register(
            @Schema(description = "대여자 ID")
            @Min(value = 1, message = "대여자 ID는 1보다 작을 수 없습니다.")
            @NotBlank(message = "대여자 ID를 입력해야 합니다.")
            @JsonProperty(value = "renter_id", required = true)
            long userId,
            @Schema(description = "도서 ID")
            @Min(value = 1, message = "도서 ID는 1보다 작을 수 없습니다.")
            @NotBlank(message = "도서 ID를 입력해야 합니다.")
            @JsonProperty(value = "book_id", required = true)
            long bookId,
            @Schema(description = "대여 일수")
            @Min(value = 1, message = "대여 일수는 1일보다 적을 수 없습니다.")
            @Max(value = 14, message = "대여 일수는 2주를 초과할 수 없습니다.")
            @NotBlank(message = "대여 일수를 입력해야 합니다.")
            @JsonProperty(value = "rent_day", required = true)
            int rentDays
    ){}

}
