package yummy_ground.yummygyudon.obms.external.db.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;

import yummy_ground.yummygyudon.obms.app.domain.Book;
import yummy_ground.yummygyudon.obms.support.db.StringListConverter;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(schema = "book_management_system", name = "books")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class BookEntity extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "tag", columnDefinition = "text")
    @Convert(converter = StringListConverter.class)
    private List<String> tags;

    @Column(name = "publish_at", nullable = false)
    private LocalDate publishDate;

    @Builder(access = PRIVATE)
    public BookEntity(Long id, String title, String author, List<String> tags, LocalDate publishDate) {
        setId(id);
        this.title = title;
        this.author = author;
        this.tags = tags;
        this.publishDate = publishDate;
    }

    public Book toDomain() {
        return Book.of(
                this.getId(),
                this.getTitle(),
                this.getAuthor(),
                this.getTags(),
                this.getPublishDate(),
                this.getCreatedDate().toLocalDate(),
                this.getUpdatedDate().toLocalDate()
        );
    }

    public static BookEntity fromDomain(Book book) {
        return BookEntity.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .tags(book.getTags())
                .publishDate(book.getPublishAt())
                .build();
    }
}
