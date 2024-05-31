package de.eugen.brownbag.bookstoreserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.eugen.brownbag.bookstoreserver.entity.Book;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public record BookRecord(long id, String title, String author, String genre, String description,
		String isbn, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @JsonFormat(pattern="yyyy-MM-dd") LocalDate publish_date,
		String status, short rating, byte[] image, LocalDateTime created_at, LocalDateTime updated_at) {

	public BookRecord(Book book) throws SQLException {
		this(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(),
				book.getIsbn(), book.getPublishDate(), book.getStatus(), book.getRating(),
				book.getImage(), book.getCreatedAt(), book.getUpdatedAt());
	}
}
