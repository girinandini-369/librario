package com.librario.entity;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.constraints.*;

@Entity
@Table(name = "book") // matches your existing table
=======
import lombok.Data;

@Entity
@Table(name = "books")
@Data
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false) @NotBlank
    private String title;

    @Column(nullable = false) @NotBlank
    private String author;

    private String genre;

    private String publisher;

    @Column(unique = false) // keep false if your table doesn't enforce unique yet
    private String isbn;

    @Column(name = "year")
    private Integer year;

    @Column(name = "total_copies", nullable = false)
    @Min(0)
    private Integer totalCopies;

    @Column(name = "available_copies", nullable = false)
    @Min(0)
    private Integer availableCopies;

    // e.g. AVAILABLE, OUT_OF_STOCK, INACTIVE
    @Column(nullable = false)
    private String status;

    private String bookshelf;

    // --- getters/setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Integer getTotalCopies() { return totalCopies; }
    public void setTotalCopies(Integer totalCopies) { this.totalCopies = totalCopies; }

    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer availableCopies) { this.availableCopies = availableCopies; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBookshelf() { return bookshelf; }
    public void setBookshelf(String bookshelf) { this.bookshelf = bookshelf; }
=======
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private int year;
    private String isbn;

    private int totalCopies;
    private int availableCopies;

    private String status; // AVAILABLE, UNAVAILABLE
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
}
