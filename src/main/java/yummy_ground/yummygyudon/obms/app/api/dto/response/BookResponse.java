package yummy_ground.yummygyudon.obms.app.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class BookResponse {
        @Schema(title = "도서 목록 응답")
        public record All(
                @ArraySchema(schema = @Schema(implementation = Detail.class))
                @JsonProperty("books")
                List<Detail> books

        ){}

    @Schema(title = "도서 상세 정보 응답")
    public record Detail(
            @Schema(title = "도서 상세 정보 응답")
            @JsonProperty("book_id")
            long id,
            @Schema(title = "도서 상세 정보 응답")
            @JsonProperty("book_title")
            String title,
            @Schema(title = "도서 상세 정보 응답")
            @JsonProperty("book_author")
            String author,
            @Schema(title = "도서 상세 정보 응답")
            @JsonProperty("book_tags")
            List<String> tags,
            @Schema(title = "도서 상세 정보 응답")
            @JsonProperty("book_publish_date")
            LocalDate publishAt,
            @Schema(title = "도서 상세 정보 응답")
            @JsonProperty("book_register_date")
            LocalDate registerAt,
            @Schema(title = "도서 상세 정보 응답")
            @JsonProperty("book_update_date")
            LocalDate updateAt
    ){}

}
