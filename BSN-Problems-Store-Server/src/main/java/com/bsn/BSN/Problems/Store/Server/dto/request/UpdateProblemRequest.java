package com.bsn.BSN.Problems.Store.Server.dto.request;

import lombok.*;
import jakarta.validation.constraints.Pattern;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdateProblemRequest {

    private String title;
    private String difficulty;
    private Integer difficultyScore;
    private String patterns;
    private String topics;
    private String askedIn;

    @Pattern(regexp = "[YN]")
    private String isPremium;

    private String status;
    private String problemStatement;
}

