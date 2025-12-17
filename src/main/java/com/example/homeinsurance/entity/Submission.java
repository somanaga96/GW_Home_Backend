package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String submissionNumber;

    @Column(nullable = false)
    private String status;
    // DRAFT → IN_PROGRESS → QUOTED → BOUND

    private LocalDate createdDate;

    // ⭐ Many Submissions → One Account
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // ⭐ One Submission → One HomeDetails
    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL)
    private HomeDetails homeDetails;

    // ⭐ One Submission → Many Claims
    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Claim> claims = new ArrayList<>();

    // ⭐ One Submission → One Quote
    @OneToOne(mappedBy = "submission", cascade = CascadeType.ALL)
    private Quote quote;

    // ===== Policy Info fields =====
    private LocalDate coverStartDate;

    private String promoName;

    private String promoOverride;

    private String crossSell;   // YES / NO

    private String offering;    // BUILDINGS / CONTENTS / BOTH
}
