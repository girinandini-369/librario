package com.librario.controller;

import com.librario.entity.Book;
import com.librario.service.BookService;
<<<<<<< HEAD
=======
import lombok.RequiredArgsConstructor;
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/book") // ✅ singular as per requirement
=======
@RequestMapping("/api/books")
@RequiredArgsConstructor
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
public class BookController {

    private final BookService bookService;

<<<<<<< HEAD
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ------------------ CRUD ------------------

    // ✅ Add a new book
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    // ✅ Update existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    // ✅ Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // ✅ Get single book by ID
=======
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = bookService.saveBook(book);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

<<<<<<< HEAD
    // ------------------ Simple Searches ------------------

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> searchByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.searchByGenre(genre));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> searchByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(bookService.searchByAuthor(author));
    }

    @GetMapping("/publisher/{publisher}")
    public ResponseEntity<List<Book>> searchByPublisher(@PathVariable String publisher) {
        return ResponseEntity.ok(bookService.searchByPublisher(publisher));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookService.searchByTitle(title));
    }

    // ------------------ Advanced Search (Filters + Pagination) ------------------

    @GetMapping("/search")
    public ResponseEntity<List<Book>> advancedSearch(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String title
    ) {
        return ResponseEntity.ok(bookService.advancedSearch(genre, author, publisher, title));
    }
}
=======
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updated = bookService.updateBook(id, book);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
