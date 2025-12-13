package com.example.homeinsurance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDTO {

    private Long id;

    private String submissionNumber;

    @NotNull(message = "Account ID is required")
    private Long accountId;

    private String status;

    private LocalDate createdDate;
}
