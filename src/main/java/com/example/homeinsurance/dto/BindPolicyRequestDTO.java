package com.example.homeinsurance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BindPolicyRequestDTO {

    @NotNull(message = "paymentPlanType must not be null")
    private String paymentPlanType;   // SINGLE_PAYMENT / DEPOSIT_AND_INSTALLMENTS / FULL_INSTALLMENTS

    @NotNull(message = "paymentMethod must not be null")
    private String paymentMethod;     // CARD / ACCOUNT

    private Integer preferredPaymentDate;
    private Boolean autoRenew;
}
