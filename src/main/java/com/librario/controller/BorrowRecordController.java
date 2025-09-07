package com.librario.controller;

import com.librario.dto.ReturnRequest;
import com.librario.entity.BorrowRecord;
import com.librario.service.BorrowRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    private final BorrowRecordService service;

    public BorrowRecordController(BorrowRecordService service) {
        this.service = service;
    }

    // Borrow a book
    @PostMapping("/borrow")
    public BorrowRecord borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return service.borrowBook(memberId, bookId);
    }

    // Return a book (with custom return date)
    @PutMapping("/{id}/return")
    public BorrowRecord returnBook(@PathVariable Long id, @RequestBody ReturnRequest request) {
        return service.returnBook(id, request.getReturnDate());
    }

    // Get all borrow records
    @GetMapping
    public List<BorrowRecord> getAllRecords() {
        return service.getAllRecords();
    }

    // Get borrow records by member
    @GetMapping("/member/{memberId}")
    public List<BorrowRecord> getRecordsByMember(@PathVariable Long memberId) {
        return service.getRecordsByMember(memberId);
    }
}
