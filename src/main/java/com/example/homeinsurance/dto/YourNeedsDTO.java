package com.example.homeinsurance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YourNeedsDTO {
    @NotNull
    private String contentsCoverAmount;
    private Boolean bikesOver500;
    private Boolean highRiskItemsOver2000;
    private Boolean totalHighRiskValue;

    private Integer buildingsSumInsured;
    private Integer contentsSumInsured;
}
