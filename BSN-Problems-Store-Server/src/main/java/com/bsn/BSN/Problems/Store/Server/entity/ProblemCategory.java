package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROBLEM_CATEGORY")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProblemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @Column(name = "CATEGORY_NAME", nullable = false, unique = true, length = 100)
    private String categoryName;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}
