package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "claims")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean anyClaimsIn5Years;
    private Boolean anyOutstandingClaims;
    private String claimFreeBuildingsYears;
    private String claimFreeContentsYears;

    // ⭐ Many Claims → One Submission
    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private Submission submission;
}
