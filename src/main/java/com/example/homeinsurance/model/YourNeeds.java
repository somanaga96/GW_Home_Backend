package com.example.homeinsurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "your_needs")
public class YourNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentsCoverAmount;

    private Boolean bikesOver500;
    private Boolean highRiskItemsOver2000;

    private Integer totalHighRiskValue;
    private Integer totalPersonalItemsCover;
}
