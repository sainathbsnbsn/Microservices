package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SOLUTION_CODE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolutionCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODE_ID")
    private Long codeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SOLUTION_ID")
    private ProblemSolution solution;

    @Column(name = "LANGUAGE", length = 50)
    private String language; // Java, C++, Python

    @Column(name = "CODE_TEXT", columnDefinition = "CLOB")
    private String codeText;
}

