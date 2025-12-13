package com.example.homeinsurance.dto;

import com.example.homeinsurance.enums.PaymentMethod;
import com.example.homeinsurance.enums.PaymentPlanType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BindPolicyRequestDTO {

    @NotNull
    private PaymentPlanType paymentPlanType;

    @NotNull
    private PaymentMethod paymentMethod;
}
