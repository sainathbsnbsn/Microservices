package com.bsn.BSN.Problems.Store.Server.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SuccessRateUpdateRequest {

    @NotNull
    @DecimalMin("0.0") @DecimalMax("100.0")
    private Double successRate;
}

