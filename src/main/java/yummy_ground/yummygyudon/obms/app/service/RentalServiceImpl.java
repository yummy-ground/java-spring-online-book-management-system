package yummy_ground.yummygyudon.obms.app.service;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import yummy_ground.yummygyudon.obms.app.domain.Rental;
import yummy_ground.yummygyudon.obms.app.domain.RentalUserInfo;
import yummy_ground.yummygyudon.obms.app.domain.RentalBookInfo;
import yummy_ground.yummygyudon.obms.app.repository.RentalRepository;


@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    @Override
    public Rental startRental(RegisterCommand command) {
        RentalUserInfo rentalUserInfo = RentalUserInfo.of(command.userId());
        RentalBookInfo rentalBookInfo = RentalBookInfo.of(command.bookId());

        LocalDate now = LocalDate.now();
        Rental newRental = Rental.of(rentalBookInfo, rentalUserInfo, now, now.plusDays(command.rentDay()), false, 0);
        return rentalRepository.save(newRental);
    }

    @Override
    public boolean isBookOnRent(long bookId) {
        return rentalRepository.isExistByBookId(bookId);
    }

    @Override
    public void endRental(long rentalId) {
        rentalRepository.deleteById(rentalId);
    }
}
