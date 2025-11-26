package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PROBLEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROBLEM_ID")
    private Long problemId;

    @Column(name = "TITLE", nullable = false, length = 255)
    private String title;

    @Column(name = "SLUG", nullable = false, unique = true, length = 255)
    private String slug;

    @Column(name = "DIFFICULTY", nullable = false, length = 20)
    private String difficulty; // Easy, Medium, Hard

    @Column(name = "DIFFICULTY_SCORE")
    private Integer difficultyScore;

    @Column(name = "PATTERNS")
    private String patterns;  // stored as comma-separated string: "Arrays,Two Pointers"

    @Column(name = "TOPICS")
    private String topics;    // stored as comma-separated string

    @Column(name = "ASKED_IN")
    private String askedIn;   // comma-separated list of companies

    @Column(name = "IS_PREMIUM")
    private Boolean isPremium;

    @Column(name = "STATUS", length = 20)
    private String status; // ACTIVE, DRAFT, ARCHIVED

    @Column(name = "POPULARITY_SCORE")
    private Integer popularityScore;

    @Column(name = "ATTEMPT_COUNT")
    private Long attemptCount;

    @Column(name = "SUCCESS_RATE")
    private Double successRate;

    @Column(name = "FREQUENCY_TAG")
    private String frequencyTag; // Daily, Weekly, Must-Do

    @Column(name = "PROBLEM_STATEMENT", columnDefinition = "CLOB")
    private String problemStatement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID") // FK column in Oracle
    private ProblemCategory category;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.popularityScore = 0;
        this.attemptCount = 0L;
        this.successRate = 0.0;
        this.status = this.status == null ? "ACTIVE" : this.status;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

