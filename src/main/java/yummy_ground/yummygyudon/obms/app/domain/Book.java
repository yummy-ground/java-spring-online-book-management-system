package yummy_ground.yummygyudon.obms.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class Book {
    private Long id;
    private String title;
    private String author;
    private List<String> tags;
    private LocalDate publishAt;
    private LocalDate registerAt;
    private LocalDate updateAt;

    public static Book of(String title, String author, List<String> tags, LocalDate publishAt) {
        return Book.builder()
                .title(title)
                .author(author)
                .tags(tags)
                .publishAt(publishAt)
                .build();
    }
    public static Book of(long id, String title, String author, List<String> tags, LocalDate publishAt) {
        return Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .tags(tags)
                .publishAt(publishAt)
                .build();
    }
    public static Book of(long id, String title, String author, List<String> tags, LocalDate publishAt, LocalDate createAt, LocalDate updateAt) {
        return Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .tags(tags)
                .publishAt(publishAt)
                .registerAt(createAt)
                .updateAt(updateAt)
                .build();
    }
}
