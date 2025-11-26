package com.example.homeinsurance.model.quote;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // summary values
    private Double totalPolicyPremium;
    private Double buildingsSumInsured;
    private Double contentsSumInsured;

    private String offering;
    private String claimFreeBuildings;
    private String claimFreeContents;

    // excesses
    private Double totalPropertyExcess;
    private Double compulsoryExcess;
    private Double voluntaryExcess;

    private Double totalSubsidenceExcess;
    private Double totalEscapeOfWaterExcess;

    // risk address display string
    @Lob
    private String riskAddress;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuoteCoverRow> coverRows;
}
