package dk.kea.pagination.service;

import dk.kea.pagination.model.Book;
import dk.kea.pagination.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    public Page<Book> sortBooksByAuthor(Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.ASC, "author");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return bookRepository.findAll(pageable);
    }
    public Page<Book> getAllBooksFiltered(String title, String author, Pageable pageable) {
        return bookRepository.findAllFiltered(title, author, pageable);
    }
}
