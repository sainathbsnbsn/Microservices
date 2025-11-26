package com.bsn.BSN.Problems.Store.Server.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateApproachRequest {

    @NotBlank
    private String approachName; // "Brute Force", "Two Pointer"

    @NotBlank
    private String tag; // "good", "better", "best"

    private String timeComplexity;
    private String spaceComplexity;
    private String explanation;
}

