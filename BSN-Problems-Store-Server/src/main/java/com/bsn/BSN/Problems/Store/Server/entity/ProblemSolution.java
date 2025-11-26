package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROBLEM_SOLUTIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOLUTION_ID")
    private Long solutionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROBLEM_ID")
    private Problem problem;

    @Column(name = "APPROACH_NAME", length = 100)
    private String approachName;    // Brute Force, Two Pointer, HashMap

    @Column(name = "TAG", length = 50)
    private String tag;             // Good, Better, Optimal, etc.

    @Column(name = "TIME_COMPLEXITY", length = 50)
    private String timeComplexity;

    @Column(name = "SPACE_COMPLEXITY", length = 50)
    private String spaceComplexity;

    @Column(name = "IS_PRIMARY_OPTIMAL", length = 1)
    private String isPrimaryOptimal; // 'Y' only for BEST approach

    @Column(name = "EXPLANATION", columnDefinition = "CLOB")
    private String explanation;
}

