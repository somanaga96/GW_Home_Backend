package com.example.homeinsurance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyDTO {
    private String policyNumber;
    private String status;
    private LocalDate effectiveDate;
    private LocalDate expiryDate;
    private Double totalPremium;

    private String paymentPlanType;
    private String paymentMethod;

    private Double depositAmount;
    private Integer installmentCount;
    private Double installmentAmount;
}
