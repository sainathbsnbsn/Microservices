package com.bsn.BSN.Problems.Store.Server.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateProblemRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String slug;

    @NotBlank
    private String difficulty;

    @Min(1) @Max(10)
    private Integer difficultyScore;

    private String patterns; // "Arrays,Two Pointers"
    private String topics;   // "Math,Graph"
    private String askedIn;  // "Amazon,Google"

    @Pattern(regexp = "[YN]")
    private String isPremium; // 'Y' or 'N'

    private String status;
    private String problemStatement;
}
