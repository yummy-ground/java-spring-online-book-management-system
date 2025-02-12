package yummy_ground.yummygyudon.obms.app.repository;

import yummy_ground.yummygyudon.obms.app.domain.Rental;

public interface RentalRepository {
    Rental save(Rental rental);
    boolean isExistByBookId(long bookId);
    void deleteById(long id);
}
