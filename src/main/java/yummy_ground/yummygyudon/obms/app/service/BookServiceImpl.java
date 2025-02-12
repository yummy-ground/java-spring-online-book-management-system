package yummy_ground.yummygyudon.obms.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yummy_ground.yummygyudon.obms.app.domain.Book;
import yummy_ground.yummygyudon.obms.app.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book registerBook(RegisterCommand command) {
        Book updatedBook = Book.of(command.title(), command.author(), command.tags(), command.publishAt());
        return bookRepository.save(updatedBook);
    }

    @Override
    public Book findBook(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllBooksAuthorIs(String author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Override
    public List<Book> findAllBooksTitleIs(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @Override
    public void updateBook(UpdateCommand command) {
        Book updatedBook = Book.of(command.id(), command.title(), command.author(), command.tags(), command.publishAt());
        bookRepository.update(updatedBook);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
