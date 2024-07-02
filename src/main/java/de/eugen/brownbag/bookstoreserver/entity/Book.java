package de.eugen.brownbag.bookstoreserver.entity;

import de.eugen.brownbag.bookstoreserver.dto.BookRecordDTO;
import jakarta.persistence.*;
import org.springframework.core.io.ByteArrayResource;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(length = 100)
    private String genre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 20)
    private String isbn;

    private LocalDate publishDate;

    @Column(length = 50)
    private String status;

    @Column(precision = 2, scale = 1)
    private short rating;

    @Lob
    private Blob image;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

	public Book() {
	}

	public Book(BookRecordDTO book) throws SQLException {
		this.setAuthor(book.author());
		this.setGenre(book.genre());
		this.setTitle(book.title());
		this.setPublishDate(book.publish_date());
		this.id = book.id();
		this.setDescription(book.description());
		this.setIsbn(book.isbn());
		this.setRating(book.rating());
        this.setStatus(book.status());
        if (book.image() != null) {
            this.setImage(new SerialBlob(book.image()));
        }
	}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

	public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public byte[] getImage() throws SQLException {
        if (image == null) {
            return null;
        }
        int blobLength = (int) this.image.length();
        byte[] blobAsBytes = this.image.getBytes(1, blobLength);

        final ByteArrayResource byteArrayResource = new ByteArrayResource(blobAsBytes);
        return byteArrayResource.getByteArray();
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}