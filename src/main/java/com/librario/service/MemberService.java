package com.librario.service;

import com.librario.entity.Member;
import com.librario.entity.MembershipPlan;
import com.librario.repository.MemberRepository;
import com.librario.repository.MembershipPlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MembershipPlanRepository planRepository;

    public MemberService(MemberRepository memberRepository, MembershipPlanRepository planRepository) {
        this.memberRepository = memberRepository;
        this.planRepository = planRepository;
    }

    // Add new member with plan
    public Member addMember(Long planId, Member member) {
        MembershipPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        member.setMembershipPlan(plan);
        member.setStartDate(LocalDate.now());
        member.setEndDate(LocalDate.now().plusDays(plan.getDurationDays())); // ✅ FIXED
        member.setStatus("ACTIVE");

        return memberRepository.save(member);
    }

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Get member by id
    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    // Update member
    public Member updateMember(Long id, Long planId, Member updatedMember) {
        Member member = getMember(id);
        MembershipPlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        member.setName(updatedMember.getName());
        member.setEmail(updatedMember.getEmail());
        member.setMembershipPlan(plan);
        member.setStartDate(updatedMember.getStartDate());
        member.setEndDate(updatedMember.getEndDate());
        member.setStatus(updatedMember.getStatus());

        return memberRepository.save(member);
    }

    // Delete member
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
