package yummy_ground.yummygyudon.obms.app.repository;

import yummy_ground.yummygyudon.obms.app.domain.Book;

import java.util.List;

public interface BookRepository {
    Book save(Book book);
    Book findById(long id);
    List<Book> findAll();
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByTitle(String title);
    void update(Book book);
    void deleteById(long id);
}
