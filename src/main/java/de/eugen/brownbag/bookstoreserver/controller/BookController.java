package de.eugen.brownbag.bookstoreserver.controller;

import de.eugen.brownbag.bookstoreserver.dto.BookRecordDTO;
import de.eugen.brownbag.bookstoreserver.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/{id}")
	public ResponseEntity<BookRecordDTO> getBookById(@PathVariable long id) {
		try {
			return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/id/all")
	public ResponseEntity<List<Long>> getAllBookIds() {
		try {
			return new ResponseEntity<>(bookService.getBookIds(), HttpStatus.OK);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping("/book")
	public ResponseEntity<BookRecordDTO> addBook(@RequestBody BookRecordDTO book) {
		try {
			return new ResponseEntity<>(bookService.createBook(book), HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/book")
	public ResponseEntity<Void> updateBook(@RequestBody BookRecordDTO book) {
		try {
			bookService.updateBook(book);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
