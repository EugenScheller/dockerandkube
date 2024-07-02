package de.eugen.brownbag.bookstoreserver.services;

import de.eugen.brownbag.bookstoreserver.dto.BookRecordDTO;
import de.eugen.brownbag.bookstoreserver.entity.Book;
import de.eugen.brownbag.bookstoreserver.repository.BookRepository;
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

	public BookRecordDTO getBook(long id) throws SQLException {
		Book book = Objects.requireNonNull(
				bookRepository.findById(id).orElse(null));
		return new BookRecordDTO(book);
	}

	public List<Long> getBookIds() throws SQLException {
		return bookRepository.findAll()
				.stream()
				.map(Book::getId)
				.collect(Collectors.toList());
	}

	public BookRecordDTO createBook(BookRecordDTO book) throws SQLException {
		return new BookRecordDTO(bookRepository.save(new Book(book)));
	}

	public void updateBook(BookRecordDTO book) throws SQLException {
		bookRepository.save(new Book(book));
	}
}
