package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandName;
    private String firstName;
    private String lastName;
    private String dateOfBirth; // keep String as requested
    private String postcode;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}


