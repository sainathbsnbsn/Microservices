package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SolutionCodeBatchResponse {
    private Long solutionId;
    private Integer codesAdded;
}

