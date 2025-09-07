package com.librario.controller;

import com.librario.dto.PenaltyRequest;
import com.librario.entity.Penalty;
import com.librario.service.PenaltyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/penalties")
public class PenaltyController {

    private final PenaltyService penaltyService;

    public PenaltyController(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    // Create a new penalty
    @PostMapping("/create")
    public ResponseEntity<Penalty> createPenalty(@RequestBody PenaltyRequest request) {
        return ResponseEntity.ok(penaltyService.createPenalty(request));
    }

    // Get all penalties
    @GetMapping
    public List<Penalty> getAllPenalties() {
        return penaltyService.getAllPenalties();
    }

    // Get penalties by member
    @GetMapping("/member/{memberId}")
    public List<Penalty> getPenaltiesByMember(@PathVariable Long memberId) {
        return penaltyService.getPenaltiesByMember(memberId);
    }

    // ✅ Get penalty summary for a member
    @GetMapping("/summary/{memberId}")
    public Map<String, Object> getPenaltySummary(@PathVariable Long memberId) {
        List<Penalty> penalties = penaltyService.getPenaltiesByMember(memberId);

        double totalAmount = penalties.stream()
                .mapToDouble(Penalty::getAmount)
                .sum();

        Map<String, Object> response = new HashMap<>();
        response.put("member_id", memberId);
        response.put("total_amount", totalAmount);
        response.put("penalties_count", penalties.size());

        return response;
    }
}
