package yummy_ground.yummygyudon.obms.external.db.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import yummy_ground.yummygyudon.obms.external.db.entity.RentalEntity;

public interface RentalJpaRepository extends JpaRepository<RentalEntity, Long> {
    boolean existsByBookId(long bookId);
    void deleteById(long id);
}
