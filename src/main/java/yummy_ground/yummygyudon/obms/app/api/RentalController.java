package yummy_ground.yummygyudon.obms.app.api;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yummy_ground.yummygyudon.obms.app.domain.Rental;
import yummy_ground.yummygyudon.obms.app.service.RentalService;
import yummy_ground.yummygyudon.obms.app.api.dto.request.RentalRequest;
import yummy_ground.yummygyudon.obms.app.api.dto.response.RentalResponse;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.support.util.ApiResponseUtil;
import yummy_ground.yummygyudon.obms.support.code.success.RentalSuccess;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
public class RentalController implements RentalApi {
    private final RentalService rentalService;

    @Override
    @PostMapping
    public ResponseEntity<BaseResponse<?>> rent(
            @RequestBody @Valid RentalRequest.RentalCreate newRental
    ) {
        Rental rental = rentalService.startRental(newRental.toCommand());
        RentalResponse.RentalRegister result = RentalResponse.RentalRegister.from(rental);
        return ApiResponseUtil.successContent(RentalSuccess.REGISTER_RENTAL, result);
    }

    @Override
    @GetMapping("/status")
    public ResponseEntity<BaseResponse<?>> isRentalExistOf(
            @RequestParam("book_id") long bookId
    ) {
        boolean bookOnRent = rentalService.isBookOnRent(bookId);
        RentalResponse.RentalStatus result = new RentalResponse.RentalStatus(bookOnRent);
        return ApiResponseUtil.successContent(RentalSuccess.GET_RENTAL_STATUS, result);
    }

    @Override
    @DeleteMapping("/{rentalId}")
    public ResponseEntity<BaseResponse<?>> returned(
            @PathVariable("rentalId") long rentalId
    ) {
        rentalService.endRental(rentalId);
        return ApiResponseUtil.successNoContent();
    }
}
