package com.bsn.BSN.Problems.Store.Server.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateSolutionCodeRequest {

    @NotBlank
    private String language;

    @NotBlank
    private String codeText;
}

