package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemTagsListResponse {
    private Long problemId;
    private List<TagResponse> tags;
}

