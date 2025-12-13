package com.example.homeinsurance.dto.reinstate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReinstatePolicyRequestDTO {

    @NotNull(message = "Reinstatement date is required")
    private LocalDate reinstateDate;

    @NotBlank(message = "Reinstatement reason is required")
    private String reason;
}
