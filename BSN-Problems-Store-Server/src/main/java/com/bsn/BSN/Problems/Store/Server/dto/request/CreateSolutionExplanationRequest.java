package com.bsn.BSN.Problems.Store.Server.dto.request;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateSolutionExplanationRequest {

    @NotBlank
    private String logic;

    private String edgeCases;
    private String dryRun;
    private String notes;
}

