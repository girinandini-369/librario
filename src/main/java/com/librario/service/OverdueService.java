package com.librario.service;

import com.librario.entity.BorrowRecord;
import com.librario.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class OverdueService {

    private final BorrowRecordRepository borrowRepo;

    public OverdueService(BorrowRecordRepository borrowRepo) {
        this.borrowRepo = borrowRepo;
    }

    public List<Map<String, Object>> checkOverdues() {
        List<BorrowRecord> records = borrowRepo.findAll();
        List<Map<String, Object>> overdueList = new ArrayList<>();

        for (BorrowRecord record : records) {
            if ("BORROWED".equals(record.getStatus())) {
                LocalDate today = LocalDate.now();
                if (today.isAfter(record.getDueDate())) {
                    long daysLate = ChronoUnit.DAYS.between(record.getDueDate(), today);

                    // Penalty logic: Rs. 5 per day
                    double fine = daysLate * 5;

                    Map<String, Object> data = new HashMap<>();
                    data.put("recordId", record.getId());
                    data.put("memberId", record.getMemberId());
                    data.put("bookId", record.getBookId());
                    data.put("dueDate", record.getDueDate());
                    data.put("daysLate", daysLate);
                    data.put("fine", fine);

                    overdueList.add(data);
                }
            }
        }
        return overdueList;
    }
}
