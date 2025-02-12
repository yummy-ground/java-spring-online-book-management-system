package yummy_ground.yummygyudon.obms.app.repository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import yummy_ground.yummygyudon.obms.app.domain.Rental;
import yummy_ground.yummygyudon.obms.support.code.error.BookError;
import yummy_ground.yummygyudon.obms.support.code.error.UserError;
import yummy_ground.yummygyudon.obms.support.exception.BookException;
import yummy_ground.yummygyudon.obms.support.exception.UserException;
import yummy_ground.yummygyudon.obms.external.db.entity.BookEntity;
import yummy_ground.yummygyudon.obms.external.db.entity.RentalEntity;
import yummy_ground.yummygyudon.obms.external.db.entity.UserEntity;
import yummy_ground.yummygyudon.obms.external.db.repository.jpa.BookJpaRepository;
import yummy_ground.yummygyudon.obms.external.db.repository.jpa.RentalJpaRepository;
import yummy_ground.yummygyudon.obms.external.db.repository.jpa.UserJpaRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository {
    private final RentalJpaRepository jpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final BookJpaRepository bookJpaRepository;

    @Override
    @Transactional
    public Rental save(Rental rental) {
        RentalEntity rentalEntity = RentalEntity.fromDomain(rental);
        UserEntity userEntity = userJpaRepository.findById(rental.getUserInfo().getUserId())
                .orElseThrow(() -> new UserException(UserError.NOT_FOUND_USER));
        BookEntity bookEntity = bookJpaRepository.findById(rental.getBookInfo().getBookId())
                .orElseThrow(() -> new BookException(BookError.NOT_FOUND_BOOK));

        rentalEntity.setUser(userEntity);
        rentalEntity.setBook(bookEntity);
        RentalEntity savedRentalEntity = jpaRepository.save(rentalEntity);
        return savedRentalEntity.toDomain();
    }

    @Override
    public boolean isExistByBookId(long bookId) {
        return jpaRepository.existsByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        jpaRepository.deleteById(id);
    }
}
