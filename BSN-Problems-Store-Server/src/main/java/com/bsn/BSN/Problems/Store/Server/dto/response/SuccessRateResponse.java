package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SuccessRateResponse {
    private Long problemId;
    private Double successRate;
}

