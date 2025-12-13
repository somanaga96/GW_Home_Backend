package com.example.homeinsurance.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteDTO {

    private String quoteNumber;
    private Double basePremium;
    private Double claimLoading;
    private Double totalPremium;
    private String submissionNumber;
}
