package com.example.homeinsurance.dto;

import com.example.homeinsurance.exception.YesNoBooleanDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDetailsDTO {

    @NotBlank
    private String riskAddress;

    @NotBlank
    private String homeType;

    @NotNull
    private Integer builtYear;

    @NotNull
    private Integer bedRooms;

    @JsonDeserialize(using = YesNoBooleanDeserializer.class)
    private Boolean hasSecurityAlarm;
}
