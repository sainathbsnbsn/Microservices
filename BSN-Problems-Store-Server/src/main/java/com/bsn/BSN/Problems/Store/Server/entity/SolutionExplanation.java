package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SOLUTION_EXPLANATION")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SolutionExplanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXPLANATION_ID")
    private Long explanationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOLUTION_ID")
    private ProblemSolution problemSolution;

    @Column(name = "LOGIC", columnDefinition = "CLOB")
    private String logic;

    @Column(name = "EDGE_CASES", columnDefinition = "CLOB")
    private String edgeCases;

    @Column(name = "DRY_RUN", columnDefinition = "CLOB")
    private String dryRun;

    @Column(name = "NOTES", length = 255)
    private String notes;
}

