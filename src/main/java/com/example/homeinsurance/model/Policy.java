package com.example.homeinsurance.model;

import com.example.homeinsurance.model.quote.Quote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // ACTIVE / CANCELLED

    @OneToOne
    @JoinColumn(name = "quote_id")
    @JsonBackReference("quote-policy")   // FIXED — must match Quote entity
    private Quote quote;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference  // should be BackReference
//    @JsonBackReference("account-policies")   // should be BackReference
    private Account account;
}
