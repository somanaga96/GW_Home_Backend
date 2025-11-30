package com.example.homeinsurance.model.home;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "property_details")
public class PropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // BACK reference to HomeDetails
    @OneToOne(mappedBy = "propertyDetails")
    @JsonBackReference("home-property")
    private HomeDetails homeDetails;

    private String riskAddress;
    private String homeType;
    private String ownership;
    private int builtYear;
    private String yearsAtProperty;
    private String listedBuilding;
    private int bedrooms;
    private int bathrooms;
    private String exteriorWalls;
    private String roofMadeOf;
    private String partRoofFlat;
    private String goodStateRepair;

    private Boolean subsidence;
    private Boolean flood10Years;
    private Boolean floodingRisk;
    private Boolean isCurrentlyFlooded;

    private int adultsLiving;
    private int childrenLiving;
    private Boolean permanentResidence;
    private Boolean empty30Days;
    private Boolean businessUse;
}
