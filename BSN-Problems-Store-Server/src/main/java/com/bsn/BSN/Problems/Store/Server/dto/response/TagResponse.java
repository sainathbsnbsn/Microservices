package com.bsn.BSN.Problems.Store.Server.dto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagResponse {
    private Long tagId;
    private String tagName;
    private String description;
}

