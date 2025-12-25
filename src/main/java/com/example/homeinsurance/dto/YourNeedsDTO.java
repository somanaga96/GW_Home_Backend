package com.example.homeinsurance.dto;

import com.example.homeinsurance.exception.YesNoBooleanDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YourNeedsDTO {
    @NotNull
    private String contentsCoverAmount;
    private Boolean totalHighRiskValue;

    private Integer buildingsSumInsured;
    private Integer contentsSumInsured;
    @JsonDeserialize(using = YesNoBooleanDeserializer.class)
    private Boolean bikesOver500;

    @JsonDeserialize(using = YesNoBooleanDeserializer.class)
    private Boolean highRiskItemsOver2000;

}
