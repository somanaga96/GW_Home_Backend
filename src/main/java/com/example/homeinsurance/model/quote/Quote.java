package com.example.homeinsurance.model.quote;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.model.claims.ClaimsPageData;
import com.example.homeinsurance.model.home.HomeDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quoteNumber;
    private LocalDate createdDate;
    private String status;

    // ---- Submission (One-to-One)
    @OneToOne
    @JoinColumn(name = "submission_id")
    @JsonBackReference("submission-quote")
    private Submission submission;

    // ---- Account (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference("account-quotes")
    private Account account;

    // ---- Policy (One-to-One)
    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    @JsonManagedReference("quote-policy")
    private Policy policy;

    // ---- Home Details (One-to-One)
    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    @JsonManagedReference("quote-home")
    private HomeDetails homeDetails;

    // ---- Claims Page (One-to-One)
    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    @JsonManagedReference("quote-claims")
    private ClaimsPageData claimsPage;
}
