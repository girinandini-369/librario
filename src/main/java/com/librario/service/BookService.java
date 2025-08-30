package com.librario.service;

import com.librario.entity.Book;
import com.librario.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // Save a new book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Update book by ID
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setAuthor(bookDetails.getAuthor());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setPublisher(bookDetails.getPublisher());
        existingBook.setYear(bookDetails.getYear());
        existingBook.setIsbn(bookDetails.getIsbn());
        existingBook.setTotalCopies(bookDetails.getTotalCopies());
        existingBook.setAvailableCopies(bookDetails.getAvailableCopies());
        existingBook.setStatus(bookDetails.getStatus());
        return bookRepository.save(existingBook);
    }

    // Delete book by ID
    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }
}
