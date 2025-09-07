package com.librario.service;

import com.librario.entity.MembershipPlan;
import com.librario.repository.MembershipPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipPlanService {

    private final MembershipPlanRepository planRepository;

    public MembershipPlanService(MembershipPlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    // Add new plan
    public MembershipPlan addPlan(MembershipPlan plan) {
        return planRepository.save(plan);
    }

    // Get all plans
    public List<MembershipPlan> getAllPlans() {
        return planRepository.findAll();
    }

    // Get by id
    public MembershipPlan getPlan(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));
    }

    // Update plan
    public MembershipPlan updatePlan(Long id, MembershipPlan updatedPlan) {
        MembershipPlan plan = getPlan(id);
        plan.setType(updatedPlan.getType());
        plan.setFees(updatedPlan.getFees());
        plan.setBorrowingLimit(updatedPlan.getBorrowingLimit());
        plan.setDurationDays(updatedPlan.getDurationDays());
        return planRepository.save(plan);
    }

    // Delete plan
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }
}
