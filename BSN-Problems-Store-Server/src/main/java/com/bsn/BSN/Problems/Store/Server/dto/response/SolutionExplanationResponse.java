package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SolutionExplanationResponse {

    private Long explanationId;
    private Long solutionId;
    private String logic;
    private String edgeCases;
    private String dryRun;
    private String notes;
}

