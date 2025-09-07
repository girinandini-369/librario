package com.librario.service;

import com.librario.entity.Book;
import com.librario.repository.BookRepository;
<<<<<<< HEAD
import com.librario.service.spec.BookSpecification;
=======
import lombok.RequiredArgsConstructor;
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
import org.springframework.stereotype.Service;

import java.util.List;

@Service
<<<<<<< HEAD
=======
@RequiredArgsConstructor
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
public class BookService {

    private final BookRepository bookRepository;

<<<<<<< HEAD
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
=======
    // Save a new book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

<<<<<<< HEAD
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
=======
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
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
    }
}
