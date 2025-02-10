package yummy_ground.yummygyudon.obms.external.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ForeignKey;
import org.hibernate.annotations.ColumnDefault;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(schema = "book_management_system", name = "rentals")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class RentalEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USER"), nullable = false)
    private UserEntity user;

    @ManyToOne
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

}
