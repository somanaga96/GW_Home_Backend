package com.example.homeinsurance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimDTO {

    private Long id;

    @NotNull(message = "anyClaimsIn5Years is required")
    private Boolean anyClaimsIn5Years;

    @NotNull(message = "anyOutstandingClaims is required")
    private Boolean anyOutstandingClaims;

    @NotBlank(message = "claimFreeBuildingsYears is required")
    private String claimFreeBuildingsYears;

    @NotBlank(message = "claimFreeContentsYears is required")
    private String claimFreeContentsYears;

    // UI convenience
    private String submissionNumber;
}
