package com.example.homeinsurance.model;

import com.example.homeinsurance.model.quote.Quote;
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
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submissionNumber;
    private String status;
    private LocalDate createdDate;

    // Many submissions → One account
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference("account-submissions")
    private Account account;

    // One submission → One quote
    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL)
    @JsonManagedReference("submission-quote")
    private Quote quote;
}

