package yummy_ground.yummygyudon.obms.app.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class RentalBookInfo {
    private long bookId;
    private String bookTitle;
    private String bookAuthor;

    public static RentalBookInfo of(long id) {
        return RentalBookInfo.builder()
                .bookId(id)
                .build();
    }

    public static RentalBookInfo of(long id, String title, String author) {
        return RentalBookInfo.builder()
                .bookId(id)
                .bookTitle(title)
                .bookAuthor(author)
                .build();
    }
}
