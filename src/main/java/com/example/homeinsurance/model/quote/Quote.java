package com.example.homeinsurance.model.quote;

import com.example.homeinsurance.model.Submission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quoteNumber;   // Q-900012
    private String status;        // DRAFT / GENERATED / PURCHASED
    private Double premium;       // Example: 421.87
    private Double buildingSumInsured;
    private Double contentSumInsured;

    // ⭐ Many quotes → One submission
    @ManyToOne
    @JoinColumn(name = "submission_id")
    @JsonBackReference
    private Submission submission;
}
