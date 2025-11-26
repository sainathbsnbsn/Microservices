package com.bsn.BSN.Problems.Store.Server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAGS")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Long tagId;

    @Column(name = "TAG_NAME", nullable = false, unique = true, length = 100)
    private String tagName;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}

