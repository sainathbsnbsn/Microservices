package com.bsn.BSN.Problems.Store.Server.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemTagMappingResponse {
    private Long problemId;
    private Long tagId;
    private Boolean mapped;
}

