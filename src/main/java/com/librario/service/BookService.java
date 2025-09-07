package com.librario.service;

import com.librario.entity.Book;
import com.librario.repository.BookRepository;
import com.librario.service.spec.BookSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ------------------ CRUD ------------------

    // ✅ Add new book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // ✅ Update book
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
                    existingBook.setBookshelf(updatedBook.getBookshelf());
                    existingBook.setGenre(updatedBook.getGenre());
                    existingBook.setIsbn(updatedBook.getIsbn());
                    existingBook.setPublisher(updatedBook.getPublisher());
                    existingBook.setStatus(updatedBook.getStatus());
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setTotalCopies(updatedBook.getTotalCopies());
                    existingBook.setYear(updatedBook.getYear());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // ✅ Delete book
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    // ✅ Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ✅ Get book by ID
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // ------------------ Simple Searches ------------------

    public List<Book> searchByGenre(String genre) {
        return bookRepository.findAll(BookSpecification.hasGenre(genre));
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findAll(BookSpecification.hasAuthor(author));
    }

    public List<Book> searchByPublisher(String publisher) {
        return bookRepository.findAll(BookSpecification.hasPublisher(publisher));
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findAll(BookSpecification.hasTitle(title));
    }

    // ------------------ Advanced Search ------------------

    public List<Book> advancedSearch(String genre, String author, String publisher, String title) {
        return bookRepository.findAll(
                BookSpecification.advancedSearch(genre, author, publisher, title)
        );
    }
}
