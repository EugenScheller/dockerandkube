package de.eugen.brownbag.bookstoreserver.controller;

import de.eugen.brownbag.bookstoreserver.dto.BookRecord;
import de.eugen.brownbag.bookstoreserver.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/{id}")
	public ResponseEntity<BookRecord> getBookById(@PathVariable long id) {
		try {
			return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/id/all")
	public ResponseEntity<List<Long>> getAllBooks() {
		try {
			return new ResponseEntity<>(bookService.getBookIds(), HttpStatus.OK);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
