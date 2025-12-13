package com.example.homeinsurance.dto;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {

    private Long id;

    @NotBlank(message = "Brand name is required")
    private String brandName;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Date of birth is required")
    // Optional: simple date format validation
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "Date of birth must be in yyyy-MM-dd format"
    )
    private String dateOfBirth;

    @NotBlank(message = "Postcode is required")
    private String postcode;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}

