package com.librario.repository;

import com.librario.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    List<BorrowRecord> findByMemberId(Long memberId);

    long countByMemberIdAndReturnDateIsNull(Long memberId); // to enforce borrow limit later
}
