package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROBLEM_EXAMPLES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemExample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXAMPLE_ID")
    private Long exampleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROBLEM_ID")
    private Problem problem;

    @Column(name = "INPUT", columnDefinition = "CLOB")
    private String input;

    @Column(name = "OUTPUT", columnDefinition = "CLOB")
    private String output;

    @Column(name = "EXPLANATION", columnDefinition = "CLOB")
    private String explanation;
}
