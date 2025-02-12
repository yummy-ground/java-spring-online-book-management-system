package yummy_ground.yummygyudon.obms.app.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import yummy_ground.yummygyudon.obms.app.domain.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    Book registerBook(RegisterCommand command);
    Book findBook(long id);
    List<Book> findAllBooks();
    List<Book> findAllBooksAuthorIs(String author);
    List<Book> findAllBooksTitleIs(String title);

    void updateBook(UpdateCommand command);

    void deleteBook(long id);

    record RegisterCommand(
            String title,
            String author,
            List<String> tags,
            LocalDate publishAt
    ){}

    record UpdateCommand(
            long id,
            String title,
            String author,
            List<String> tags,
            LocalDate publishAt
    ){}

}
