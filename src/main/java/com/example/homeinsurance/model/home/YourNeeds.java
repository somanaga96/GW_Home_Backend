package com.example.homeinsurance.model.home;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "your_needs")
public class YourNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // BACK reference to HomeDetails
    @OneToOne(mappedBy = "yourNeeds")
    @JsonBackReference("home-needs")
    private HomeDetails homeDetails;

    private String contentsCoverAmount;
    private Boolean bikesOver500;
    private Boolean highRiskItemsOver2000;

    private Integer totalHighRiskValue;
    private Integer totalPersonalItemsCover;
}
