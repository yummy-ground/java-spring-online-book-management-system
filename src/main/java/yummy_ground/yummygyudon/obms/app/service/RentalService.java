package yummy_ground.yummygyudon.obms.app.service;

import yummy_ground.yummygyudon.obms.app.domain.Rental;

public interface RentalService {
    Rental startRental(RegisterCommand command);
    boolean isBookOnRent(long bookId);
    void endRental(long rentalId);

    record RegisterCommand(
            long bookId,
            long userId,
            int rentDay
    ){}
}
