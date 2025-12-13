package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "your_needs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YourNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentsCoverAmount;
    private Boolean bikesOver500;
    private Boolean highRiskItemsOver2000;
    private Boolean totalHighRiskValue;

    private Integer buildingsSumInsured;
    private Integer contentsSumInsured;

    // ⭐ Back reference
    @OneToOne
    @JoinColumn(name = "home_details_id")
    private HomeDetails homeDetails;
}
