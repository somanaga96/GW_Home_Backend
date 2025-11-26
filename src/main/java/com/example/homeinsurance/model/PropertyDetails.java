package com.example.homeinsurance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "policy_details")
public class PropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // LEFT COLUMN
    private String riskAddress;
    private String homeType;
    private String ownership;
    private String builtYear;
    private String yearsAtProperty;
    private String listedBuilding;
    private String bedrooms;
    private String bathrooms;
    private String exteriorWalls;
    private String roofMadeOf;
    private String partRoofFlat;
    private String goodStateRepair;

    // RIGHT COLUMN
    private String subsidence;
    private String flood10Years;
    private String floodingRisk;
    private Boolean isCurrentlyFlooded;

    // Occupants
    private String adultsLiving;
    private String childrenLiving;
    private String permanentResidence;
    private String empty30Days;
    private String businessUse;
}