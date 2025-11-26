package com.bsn.BSN.Problems.Store.Server.dto.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AttemptIncrementResponse {
    private Long problemId;
    private Long attemptCount;
}

