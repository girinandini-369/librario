package com.librario.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String genre;
    private String publisher;
    private int year;
    private String isbn;

    private int totalCopies;
    private int availableCopies;

    private String status; // AVAILABLE, UNAVAILABLE
}
