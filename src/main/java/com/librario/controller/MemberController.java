package com.librario.controller;

import com.librario.entity.Member;
import com.librario.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/add/{planId}")
    public Member addMember(@PathVariable Long planId, @RequestBody Member member) {
        return memberService.addMember(planId, member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.getMember(id);
    }

    @PutMapping("/{id}/{planId}")
    public Member updateMember(@PathVariable Long id,
                               @PathVariable Long planId,
                               @RequestBody Member member) {
        return memberService.updateMember(id, planId, member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
