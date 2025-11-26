package com.bsn.BSN.Problems.Store.Server.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SolutionCodeBatchRequest {

    @NotEmpty
    private List<CreateSolutionCodeRequest> codes;
}

