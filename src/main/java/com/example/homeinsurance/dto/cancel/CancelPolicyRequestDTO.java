package com.example.homeinsurance.dto.cancel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelPolicyRequestDTO {

    @NotNull(message = "Cancel date is required")
    private LocalDate cancelDate;

    @NotBlank(message = "Cancellation reason is required")
    private String reason;

    @NotBlank(message = "Cancellation type is required")
    private String cancelType; // FLAT / PRO_RATA
}
