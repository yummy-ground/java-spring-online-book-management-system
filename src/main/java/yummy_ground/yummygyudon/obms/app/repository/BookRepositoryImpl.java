package yummy_ground.yummygyudon.obms.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yummy_ground.yummygyudon.obms.app.domain.Book;
import yummy_ground.yummygyudon.obms.external.db.entity.BookEntity;
import yummy_ground.yummygyudon.obms.external.db.repository.jpa.BookJpaRepository;
import yummy_ground.yummygyudon.obms.support.code.error.BookError;
import yummy_ground.yummygyudon.obms.support.exception.BookException;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookJpaRepository jpaRepository;

    @Override
    @Transactional
    public Book save(Book book) {
        BookEntity newBookEntity = BookEntity.fromDomain(book);
        BookEntity savedEntity = jpaRepository.save(newBookEntity);
        return savedEntity.toDomain();
    }

    @Override
    public Book findById(long id) {
        BookEntity findBookEntity = jpaRepository.findById(id)
                .orElseThrow(() -> new BookException(BookError.NOT_FOUND_BOOK));
        return findBookEntity.toDomain();
    }

    @Override
    public List<Book> findAll() {
        List<BookEntity> findAllBooks = jpaRepository.findAll();
        return findAllBooks.stream()
                .map(BookEntity::toDomain)
                .toList();
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        List<BookEntity> allByAuthor = jpaRepository.findAllByAuthor(author);
        return allByAuthor.stream()
                .map(BookEntity::toDomain)
                .toList();
    }

    @Override
    public List<Book> findAllByTitle(String title) {
        List<BookEntity> allByTitle = jpaRepository.findAllByTitle(title);
        return allByTitle.stream()
                .map(BookEntity::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void update(Book book) {
        jpaRepository.update(book.getId(), book.getTitle(), book.getAuthor(), book.getTags(), book.getPublishAt());
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        jpaRepository.deleteById(id);
    }
}
