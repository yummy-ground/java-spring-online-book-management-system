package yummy_ground.yummygyudon.obms.external.db.entity;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.OneToMany;

import yummy_ground.yummygyudon.obms.support.db.StringListConverter;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(schema = "book_management_system", name = "books")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
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

    @Column(name = "is_rented", nullable = false)
    private boolean isRented;

    @OneToMany(targetEntity = RentalEntity.class, mappedBy = "book")
    private List<RentalEntity> rentals = new ArrayList<>();

}
