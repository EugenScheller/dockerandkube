package de.eugen.brownbag.bookstoreserver.repository;

import de.eugen.brownbag.bookstoreserver.dto.BookRecord;
import de.eugen.brownbag.bookstoreserver.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}