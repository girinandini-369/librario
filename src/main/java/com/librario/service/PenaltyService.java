package com.librario.service;

import com.librario.dto.PenaltyRequest;
import com.librario.entity.Member;
import com.librario.entity.Penalty;
import com.librario.repository.MemberRepository;
import com.librario.repository.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PenaltyService {

    private final PenaltyRepository penaltyRepository;
    private final MemberRepository memberRepository;

    public PenaltyService(PenaltyRepository penaltyRepository, MemberRepository memberRepository) {
        this.penaltyRepository = penaltyRepository;
        this.memberRepository = memberRepository;
    }

    // ✅ Create penalty with memberId
    public Penalty createPenalty(PenaltyRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Penalty penalty = new Penalty();
        penalty.setMember(member);
        penalty.setAmount(request.getAmount());
        penalty.setReason(request.getReason());
        penalty.setCreatedAt(LocalDateTime.now());

        return penaltyRepository.save(penalty);
    }

    public List<Penalty> getAllPenalties() {
        return penaltyRepository.findAll();
    }

    public List<Penalty> getPenaltiesByMember(Long memberId) {
        return penaltyRepository.findByMemberId(memberId);
    }
}
