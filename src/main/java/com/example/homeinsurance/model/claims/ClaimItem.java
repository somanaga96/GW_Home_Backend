package com.example.homeinsurance.model.claims;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "claim")
public class ClaimItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimCause;
    private String cover;
    private String dateOfLoss;
    @Column(name = "claim_value")
    private Integer value;
    private Boolean sameAddress;
    }
