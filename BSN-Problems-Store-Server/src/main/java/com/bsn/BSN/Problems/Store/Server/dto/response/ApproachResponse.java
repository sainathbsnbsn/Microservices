package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ApproachResponse {

    private Long approachId;
    private Long problemId;
    private String approachName;
    private String tag;  // good/better/best
    private String timeComplexity;
    private String spaceComplexity;
    private String explanation;
}

