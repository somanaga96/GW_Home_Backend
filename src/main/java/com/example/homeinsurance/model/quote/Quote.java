package com.example.homeinsurance.model.quote;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.model.Submission;
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

    // One Quote → One Submission
    @OneToOne
    @JoinColumn(name = "submission_id")
    @JsonBackReference("submission-quote")
    private Submission submission;

    // Many quotes → One account
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference("account-quotes")
    private Account account;

    // One Quote → One Policy
    @OneToOne(mappedBy = "quote", cascade = CascadeType.ALL)
    @JsonManagedReference("quote-policy")
    private Policy policy;
}

