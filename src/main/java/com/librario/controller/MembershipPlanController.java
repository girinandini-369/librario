package com.librario.controller;

import com.librario.entity.MembershipPlan;
import com.librario.service.MembershipPlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership/plan")
public class MembershipPlanController {

    private final MembershipPlanService planService;

    public MembershipPlanController(MembershipPlanService planService) {
        this.planService = planService;
    }

    @PostMapping("/add")
    public MembershipPlan addPlan(@RequestBody MembershipPlan plan) {
        return planService.addPlan(plan);
    }

    @GetMapping
    public List<MembershipPlan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public MembershipPlan getPlan(@PathVariable Long id) {
        return planService.getPlan(id);
    }

    @PutMapping("/{id}")
    public MembershipPlan updatePlan(@PathVariable Long id, @RequestBody MembershipPlan plan) {
        return planService.updatePlan(id, plan);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }
}
