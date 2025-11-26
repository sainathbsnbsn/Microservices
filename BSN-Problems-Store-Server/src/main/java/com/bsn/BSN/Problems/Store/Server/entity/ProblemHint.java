package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROBLEM_HINTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemHint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HINT_ID")
    private Long hintId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROBLEM_ID")
    private Problem problem;

    @Column(name = "HINT_ORDER")
    private Integer hintOrder;

    @Column(name = "HINT_TEXT", columnDefinition = "CLOB")
    private String hintText;
}

