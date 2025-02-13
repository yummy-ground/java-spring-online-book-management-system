package yummy_ground.yummygyudon.obms.app.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yummy_ground.yummygyudon.obms.app.api.dto.request.BookRequest;
import yummy_ground.yummygyudon.obms.app.api.dto.response.BookResponse;
import yummy_ground.yummygyudon.obms.app.domain.Book;
import yummy_ground.yummygyudon.obms.app.service.BookService;
import yummy_ground.yummygyudon.obms.support.code.success.BookSuccess;
import yummy_ground.yummygyudon.obms.support.dto.BaseResponse;
import yummy_ground.yummygyudon.obms.support.util.ApiResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController implements BookApi {
    private final BookService bookService;

    @Override
    @PostMapping
    public ResponseEntity<BaseResponse<?>> register(
            @RequestBody @Valid BookRequest.BookRegister newBook
    ) {
        Book savedBook = bookService.registerBook(newBook.toCommand());
        BookResponse.BookDetail result = BookResponse.BookDetail.from(savedBook);
        return ApiResponseUtil.successContent(BookSuccess.REGISTER_BOOK, result);
    }

    @Override
    @GetMapping("/{bookId}")
    public ResponseEntity<BaseResponse<?>> getDetail(
            @PathVariable("bookId") long bookId
    ) {
        Book findBook = bookService.findBook(bookId);
        BookResponse.BookDetail result = BookResponse.BookDetail.from(findBook);
        return ApiResponseUtil.successContent(BookSuccess.GET_BOOK, result);
    }

    @Override
    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAll(
            @RequestParam(value = "title", required = false) String targetTitle,
            @RequestParam(value = "author", required = false) String targetAuthor,
            @RequestParam(value = "sort", defaultValue = "title") String sortBy,
            @PageableDefault Pageable pageable
    ) {
        List<Book> books = bookService.findAllBooks();
        BookResponse.BookAll result = BookResponse.BookAll.from(books);
        return ApiResponseUtil.successContent(BookSuccess.GET_BOOKS, result);
    }

    @Override
    @PutMapping("/{bookId}")
    public ResponseEntity<BaseResponse<?>> update(
            @PathVariable("bookId") long bookId,
            @RequestBody @Valid BookRequest.BookUpdate updatedBook
    ) {
        bookService.updateBook(updatedBook.toCommand(bookId));
        return ApiResponseUtil.successNoContent();
    }

    @Override
    @DeleteMapping("/{bookId}")
    public ResponseEntity<BaseResponse<?>> delete(
            @PathVariable("bookId") long bookId
    ) {
        bookService.deleteBook(bookId);
        return ApiResponseUtil.successNoContent();
    }
}
