package dk.kea.pagination.restController;

import dk.kea.pagination.model.Book;
import dk.kea.pagination.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            Pageable pageable) {
        // Check if title and author parameters are provided and apply filtering if needed
        if (title != null || author != null) {
            return bookService.getAllBooksFiltered(title, author, pageable);
        } else {
            return bookService.getAllBooks(pageable);
        }
    }
    @GetMapping("/sort")
    public Page<Book> sortBooksByAuthor(Pageable pageable) {
        return bookService.sortBooksByAuthor(pageable);
    }
}
