package yummy_ground.yummygyudon.obms.app.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import yummy_ground.yummygyudon.obms.app.service.BookService;
import yummy_ground.yummygyudon.obms.support.code.error.BookError;
import yummy_ground.yummygyudon.obms.support.exception.BookException;

import java.time.LocalDate;
import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class BookRequest {

    @Schema(title = "도서 등록 요청")
    public record BookRegister(
            @Schema(description = "도서 제목")
            @NotBlank(message = "도서 제목을 입력해야 합니다.")
            @JsonProperty("book_title")
            String title,
            @Schema(description = "도서 저자 이름")
            @NotBlank(message = "저자 이름을 입력해야 합니다.")
            @JsonProperty("book_author")
            String author,
            @ArraySchema(schema = @Schema(description = "도서 태그 목록"))
            @JsonProperty("book_tags")
            List<String> tags,
            @Schema(description = "도서 출판 일자", example = "2025-02-11")
            @NotNull(message = "출판 일자를 입력해야 합니다.")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @JsonProperty("book_publish_date")
            LocalDate publishAt
    ){
        public BookRegister {
            if (Objects.isNull(tags)) {
                tags = Collections.emptyList();
            }
        }
        public BookService.RegisterCommand toCommand() {
            return new BookService.RegisterCommand(
                    this.title,
                    this.author,
                    this.tags,
                    this.publishAt
            );
        }
    }

    @Schema(title = "도서 정보 수정 요청")
    public record BookUpdate(
            @Schema(description = "도서 제목")
            @NotBlank(message = "도서 제목을 입력해야 합니다.")
            @JsonProperty("book_title")
            String title,
            @Schema(description = "도서 저자 이름")
            @NotBlank(message = "저자 이름을 입력해야 합니다.")
            @JsonProperty("book_author")
            String author,
            @ArraySchema(schema = @Schema(description = "도서 태그 목록"))
            @JsonProperty("book_tags")
            List<String> tags,
            @Schema(description = "도서 출판 일자", example = "2025-02-11")
            @NotNull(message = "출판 일자를 입력해야 합니다.")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            @JsonProperty("book_publish_date")
            LocalDate publishAt
    ){
        public BookService.UpdateCommand toCommand(long id) {
            return new BookService.UpdateCommand(
                    id,
                    this.title,
                    this.author,
                    this.tags,
                    this.publishAt
            );
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum SortCriteria {
        TITLE("title"), PUBLISH_DATE("publish_date")
        ;
        private final String parameter;

        public static SortCriteria findByParameter(String parameter) {
            return Arrays.stream(SortCriteria.values())
                    .filter(criteria -> criteria.getParameter().equals(parameter))
                    .findAny()
                    .orElseThrow(() -> new BookException(BookError.INVALID_SORT_CRITERIA));
        }

    }

}
