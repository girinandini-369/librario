package com.librario.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "membership_plans")
public class MembershipPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // BASIC / PREMIUM

    @Column(nullable = false)
    private double fees;

    @Column(name = "borrowing_limit", nullable = false)
    private int borrowingLimit;

    @Column(name = "duration_days", nullable = false)
    private int durationDays;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getFees() { return fees; }
    public void setFees(double fees) { this.fees = fees; }

    public int getBorrowingLimit() { return borrowingLimit; }
    public void setBorrowingLimit(int borrowingLimit) { this.borrowingLimit = borrowingLimit; }

    public int getDurationDays() { return durationDays; }
    public void setDurationDays(int durationDays) { this.durationDays = durationDays; }
}
