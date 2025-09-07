package com.librario.service;

import com.librario.entity.BorrowRecord;
import com.librario.entity.Member;
import com.librario.entity.Penalty;
import com.librario.repository.BorrowRecordRepository;
import com.librario.repository.MemberRepository;
import com.librario.repository.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final PenaltyRepository penaltyRepository;
    private final MemberRepository memberRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository,
                               PenaltyRepository penaltyRepository,
                               MemberRepository memberRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.penaltyRepository = penaltyRepository;
        this.memberRepository = memberRepository;
    }

    // Borrow a book
    public BorrowRecord borrowBook(Long memberId, Long bookId) {
        BorrowRecord record = new BorrowRecord();
        record.setMemberId(memberId);
        record.setBookId(bookId);
        record.setBorrowDate(LocalDate.now());
        record.setStatus("BORROWED");
        return borrowRecordRepository.save(record);
    }

    // Return a book
    public BorrowRecord returnBook(Long id, LocalDate returnDate) {
        BorrowRecord record = borrowRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (!"BORROWED".equals(record.getStatus())) {
            throw new RuntimeException("Book already returned");
        }

        record.setReturnDate(returnDate);
        record.setStatus("RETURNED");

        // ✅ Check if late
        if (record.getDueDate() != null && returnDate.isAfter(record.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(record.getDueDate(), returnDate);

            // ✅ Fetch Member entity (because Penalty requires Member, not memberId)
            Member member = memberRepository.findById(record.getMemberId())
                    .orElseThrow(() -> new RuntimeException("Member not found"));

            Penalty penalty = new Penalty();
            penalty.setMember(member); // ✅ use Member object
            penalty.setAmount(daysLate * 10.0); // Example: ₹10 per day late
            penalty.setReason("Late return by " + daysLate + " days");
            penalty.setCreatedAt(LocalDateTime.now());

            penaltyRepository.save(penalty);
        }

        return borrowRecordRepository.save(record);
    }

    // Get all records
    public List<BorrowRecord> getAllRecords() {
        return borrowRecordRepository.findAll();
    }

    // Get records by member
    public List<BorrowRecord> getRecordsByMember(Long memberId) {
        return borrowRecordRepository.findByMemberId(memberId);
    }
}
