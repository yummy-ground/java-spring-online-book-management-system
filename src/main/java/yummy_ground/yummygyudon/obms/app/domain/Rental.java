package yummy_ground.yummygyudon.obms.app.domain;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
public class Rental {
    private Long id;
    private RentalBookInfo bookInfo;
    private RentalUserInfo userInfo;
    private LocalDate rentStartDate;
    private LocalDate rentEndDate;
    private boolean isExtend;
    private int extendCount;

    public static Rental of(RentalBookInfo bookInfo, RentalUserInfo userInfo, LocalDate rentStartDate, LocalDate rentEndDate, boolean isExtend, int extendCount) {
        return Rental.builder()
                .bookInfo(bookInfo)
                .userInfo(userInfo)
                .rentStartDate(rentStartDate)
                .rentEndDate(rentEndDate)
                .isExtend(isExtend)
                .extendCount(extendCount)
                .build();
    }
    public static Rental of(long id, RentalBookInfo bookInfo, RentalUserInfo userInfo, LocalDate rentStartDate, LocalDate rentEndDate, boolean isExtend, int extendCount) {
        return Rental.builder()
                .id(id)
                .bookInfo(bookInfo)
                .userInfo(userInfo)
                .rentStartDate(rentStartDate)
                .rentEndDate(rentEndDate)
                .isExtend(isExtend)
                .extendCount(extendCount)
                .build();
    }

}
