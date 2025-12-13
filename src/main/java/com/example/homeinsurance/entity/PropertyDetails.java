package com.example.homeinsurance.entity;

import com.example.homeinsurance.entity.HomeDetails;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "property_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String riskAddress;
    private String homeType;     // HOUSE, FLAT
    private Integer builtYear;
    private Integer bedRooms;
    private Boolean hasSecurityAlarm;

    // ⭐ Back reference
    @OneToOne
    @JoinColumn(name = "home_details_id")
    private HomeDetails homeDetails;
}
