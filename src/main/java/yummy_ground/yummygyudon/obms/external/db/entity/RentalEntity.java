package yummy_ground.yummygyudon.obms.external.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;

import org.hibernate.annotations.ColumnDefault;

import yummy_ground.yummygyudon.obms.app.domain.Rental;
import yummy_ground.yummygyudon.obms.app.domain.RentalBookInfo;
import yummy_ground.yummygyudon.obms.app.domain.RentalUserInfo;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(schema = "book_management_system", name = "rentals")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class RentalEntity extends BaseEntity {

    @Setter
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER"), nullable = false)
    private UserEntity user;

    @Setter
    @OneToOne(targetEntity = BookEntity.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "FK_BOOK"), nullable = false)
    private BookEntity book;


    @Column(name = "is_extend", columnDefinition = "boolean", nullable = false)
    @ColumnDefault("false")
    private boolean isExtend;

    @Column(name = "extend_count", nullable = false)
    @ColumnDefault("0")
    private int extendCount;

    @Column(name = "return_at", nullable = false)
    private LocalDate returnDate;

    @Builder(access = PRIVATE)
    public RentalEntity(Long id, UserEntity user, BookEntity book, boolean isExtend, int extendCount, LocalDate returnDate) {
        setId(id);
        this.user = user;
        this.book = book;
        this.isExtend = isExtend;
        this.extendCount = extendCount;
        this.returnDate = returnDate;
    }

    public Rental toDomain() {
        RentalBookInfo rentalBookInfo = RentalBookInfo.of(
                this.book.getId(),
                this.book.getTitle(),
                this.book.getAuthor()
        );
        RentalUserInfo rentalUserInfo = RentalUserInfo.of(
                this.user.getId(),
                this.user.getName()
        );
        return Rental.of(
                rentalBookInfo,
                rentalUserInfo,
                this.getCreatedDate().toLocalDate(),
                this.returnDate,
                this.isExtend,
                this.extendCount
        );
    }

    public static RentalEntity fromDomain(Rental rental) {
        return RentalEntity.builder()
                .id(rental.getId())
                .returnDate(rental.getRentEndDate())
                .isExtend(rental.isExtend())
                .extendCount(rental.getExtendCount())
                .build();
    }

}
