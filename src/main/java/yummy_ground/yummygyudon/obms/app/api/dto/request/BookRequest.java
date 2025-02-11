package yummy_ground.yummygyudon.obms.app.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class BookRequest {

    @Schema(title = "도서 등록 요청")
    public record Register(
            @Schema(description = "도서 제목")
            @NotBlank(message = "도서 제목을 입력해야 합니다.")
            @JsonProperty("book_title")
            String title,
            @Schema(description = "도서 저자 이름")
            @NotBlank(message = "저자 이름을 입력해야 합니다.")
            @JsonProperty("book_author")
            String author,
            @Schema(description = "도서 태그 목록", example = "Java,Best Practices,Programming", pattern = "^(\\w+)(,\\w+)*(\\w+)$")
            @Pattern(regexp = "^(\\w+)(,\\w+)*(\\w+)$", message = "태그가 여러 개일 경우, 쉼표를 기준으로 요소들을 입력해야 합니다.")
            @JsonProperty("book_tags")
            List<String> tags,
            @Schema(description = "도서 출판 일자", example = "2025-02-11", pattern = "^\\d{4}-\\d{2}-\\d{2}$")
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "출판 일자는 yyyy-MM-dd 형식으로 입력해야 합니다.")
            @NotBlank(message = "출판 일자를 입력해야 합니다.")
            @JsonProperty("book_publish_date")
            LocalDate publishAt
    ){
        public Register {
            if (Objects.isNull(tags)) {
                tags = Collections.emptyList();
            }
        }
    }

    @Schema(title = "도서 정보 수정 요청")
    public record Update(
            @Schema(description = "도서 제목")
            @NotBlank(message = "도서 제목을 입력해야 합니다.")
            @JsonProperty("book_title")
            String title,
            @Schema(description = "도서 저자 이름")
            @NotBlank(message = "저자 이름을 입력해야 합니다.")
            @JsonProperty("book_author")
            String author,
            @Schema(description = "도서 태그 목록", example = "Java,Best Practices,Programming")
            @Pattern(regexp = "^(\\w+)(,\\w+)*(\\w+)$", message = "태그가 여러 개일 경우, 쉼표를 기준으로 요소들을 입력해야 합니다.")
            @JsonProperty("book_tags")
            List<String> tags,
            @Schema(description = "도서 출판 일자", example = "2025-02-11")
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "출판 일자는 yyyy-MM-dd 형식으로 입력해야 합니다.")
            @NotBlank(message = "출판 일자를 입력해야 합니다.")
            @JsonProperty("book_publish_date")
            LocalDate publishAt
    ){}

}
