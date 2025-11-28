package com.example.homeinsurance.model;

import com.example.homeinsurance.model.quote.Quote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submissionNumber; // S-100001
    private String status; // DRAFT / QUOTED / BOUND
    private LocalDate createdDate;

    // ⭐ Many submissions → One account
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    // ⭐ One submission → Many quotes
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Quote> quotes = new ArrayList<>();
}
