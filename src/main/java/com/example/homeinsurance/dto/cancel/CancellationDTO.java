package com.example.homeinsurance.dto.cancel;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancellationDTO {

    private String policyNumber;
    private LocalDate cancelDate;
    private String reason;
    private String cancelType;
    private Double refundAmount;
    private String policyStatus;
}
