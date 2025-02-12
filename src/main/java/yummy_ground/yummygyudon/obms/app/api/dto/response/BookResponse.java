package yummy_ground.yummygyudon.obms.app.api.dto.response;

import java.util.List;
import java.time.LocalDate;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import yummy_ground.yummygyudon.obms.app.domain.Book;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class BookResponse {
        @Schema(title = "도서 목록 응답")
        @Builder(access = PRIVATE)
        public record BookAll(
                @ArraySchema(schema = @Schema(implementation = BookDetail.class))
                @JsonProperty("books")
                List<BookDetail> books

        ){
                public static BookResponse.BookAll from(List<Book> books) {
                        List<BookDetail> resultBooks = books.stream().map(BookDetail::from).toList();
                        return BookAll.builder()
                                .books(resultBooks)
                                .build();
                }
        }

    @Schema(title = "도서 상세 정보 응답")
    @Builder(access = PRIVATE)
    public record BookDetail(
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
    ){
            public static BookResponse.BookDetail from(Book book) {
                    return BookDetail.builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .author(book.getAuthor())
                            .tags(book.getTags())
                            .publishAt(book.getPublishAt())
                            .registerAt(book.getRegisterAt())
                            .updateAt(book.getUpdateAt())
                            .build();
            }
    }

}
