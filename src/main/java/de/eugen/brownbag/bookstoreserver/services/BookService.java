package de.eugen.brownbag.bookstoreserver.services;

import de.eugen.brownbag.bookstoreserver.dto.BookRecord;
import de.eugen.brownbag.bookstoreserver.repository.BookRepository;
import de.eugen.brownbag.bookstoreserver.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public BookRecord getBook(long id) throws SQLException {
		return new BookRecord(Objects.requireNonNull(bookRepository.findById(id).orElse(null)));
	}

	public List<Long> getBookIds() throws SQLException {
		return bookRepository.findAll().stream().map(Book::getId).collect(Collectors.toList());
	}
}
