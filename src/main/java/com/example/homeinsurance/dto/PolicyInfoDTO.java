package com.example.homeinsurance.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyInfoDTO {

    private LocalDate coverStartDate;
    private String promoName;
    private String promoOverride;
    private String crossSell;
    private String offering;
}
