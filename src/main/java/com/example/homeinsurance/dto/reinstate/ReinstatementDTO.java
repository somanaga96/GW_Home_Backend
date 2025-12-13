package com.example.homeinsurance.dto.reinstate;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReinstatementDTO {

    private String policyNumber;
    private LocalDate reinstateDate;
    private String reason;
    private String policyStatus;
}
