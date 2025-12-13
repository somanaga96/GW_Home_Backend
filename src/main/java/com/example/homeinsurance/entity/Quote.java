package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String quoteNumber;

    private Double basePremium;
    private Double claimLoading;
    private Double totalPremium;

    // ⭐ One Quote → One Submission
    @OneToOne
    @JoinColumn(name = "submission_id", nullable = false, unique = true)
    private Submission submission;

    // ⭐ One Quote → One Submission
    @OneToOne
    @JoinColumn(name = "policy_id",  unique = true)
    private Policy policy;
}
