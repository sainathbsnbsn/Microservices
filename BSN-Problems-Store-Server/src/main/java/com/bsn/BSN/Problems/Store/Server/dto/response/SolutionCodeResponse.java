package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SolutionCodeResponse {

    private Long codeId;
    private Long solutionId;
    private String language;
    private String codeText;
}

