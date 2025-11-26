package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROBLEM_TAG_MAPPING")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProblemTagMapping {

    @EmbeddedId
    private ProblemTagKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("problemId")
    @JoinColumn(name = "PROBLEM_ID")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "TAG_ID")
    private Tag tag;
}

