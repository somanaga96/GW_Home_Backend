package com.example.homeinsurance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String accountNumber;

    private String brandName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String postcode;
    private String quoteRefId;
    private String address;
}
