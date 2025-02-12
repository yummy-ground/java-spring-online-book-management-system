package yummy_ground.yummygyudon.obms.app.api.dto.response;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import yummy_ground.yummygyudon.obms.app.domain.Rental;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
public final class RentalResponse {

    @Schema(title = "대여 상태 정보 응답")
    public record RentalStatus(
            @Schema(description = "대출 여부")
            @JsonProperty("is_rented")
            boolean isRented
    ){}

    @Schema(title = "대여 등록 응답")
    public record RentalRegister(
            @Schema(description = "대여 내역 ID")
            @JsonProperty("rental_id")
            long id,
            @Schema(description = "대여 도서 정보")
            @JsonProperty("rental_book_info")
            RentalBookInfo bookInfo,
            @Schema(description = "대여 사용자 정보")
            @JsonProperty("rental_user_info")
            RentalUserInfo userInfo,
            @Schema(description = "대여 일자")
            @JsonProperty("rental_rent_date")
            LocalDate rentDate,
            @Schema(description = "반납 일자")
            @JsonProperty("rental_return_date")
            LocalDate returnDate
    ){

        public static RentalRegister from(Rental rental) {
            RentalBookInfo rentalBookInfo = RentalBookInfo.from(rental);
            RentalUserInfo rentalUserInfo = RentalUserInfo.from(rental);
            return new RentalRegister(
                    rental.getId(),
                    rentalBookInfo,
                    rentalUserInfo,
                    rental.getRentStartDate(),
                    rental.getRentEndDate()
            );
        }

        @Schema(title = "대여 도서 정보 응답")
        public record RentalBookInfo(
                @Schema(description = "도서 ID")
                @JsonProperty("book_id")
                long id,
                @Schema(description = "도서 제목")
                @JsonProperty("book_title")
                String title,
                @Schema(description = "도서 저자")
                @JsonProperty("book_author")
                String author
        ){

            public static RentalBookInfo from(Rental rental) {
                return new RentalBookInfo(
                        rental.getBookInfo().getBookId(),
                        rental.getBookInfo().getBookTitle(),
                        rental.getBookInfo().getBookAuthor()
                );
            }

        }

        @Schema(title = "대여 사용자 정보 응답")
        public record RentalUserInfo(
                @Schema(description = "사용자 ID")
                @JsonProperty("user_id")
                long id,
                @Schema(description = "사용자 이름")
                @JsonProperty("user_name")
                String name
        ){

            public static RentalUserInfo from(Rental rental) {
                return new RentalUserInfo(
                        rental.getUserInfo().getUserId(),
                        rental.getUserInfo().getUserName()
                );
            }

        }
    }

}
