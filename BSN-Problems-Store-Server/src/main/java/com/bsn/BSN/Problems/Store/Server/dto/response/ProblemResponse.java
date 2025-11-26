package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProblemResponse {

    private Long problemId;
    private String title;
    private String slug;
    private String difficulty;
    private Integer difficultyScore;
    private String patterns;
    private String topics;
    private String askedIn;
    private String isPremium;
    private String status;
    private Integer popularityScore;
    private Long attemptCount;
    private Double successRate;
    private String frequencyTag;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

