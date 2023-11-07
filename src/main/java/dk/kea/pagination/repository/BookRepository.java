package dk.kea.pagination.repository;

import dk.kea.pagination.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer>
{
    @Query("SELECT b FROM Book b WHERE " +
            "(:title is null or lower(b.title) like lower(concat('%', :title, '%'))) " +
            "and (:author is null or lower(b.author) like lower(concat('%', :author, '%')))")
    Page<Book> findAllFiltered(String title, String author, Pageable pageable);
}

