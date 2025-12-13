package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "home_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ⭐ One HomeDetails → One Submission
    @OneToOne
    @JoinColumn(name = "submission_id", nullable = false, unique = true)
    private Submission submission;

    // ⭐ One HomeDetails → One PropertyDetails
    @OneToOne(mappedBy = "homeDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private PropertyDetails propertyDetails;

    // ⭐ One HomeDetails → One YourNeeds
    @OneToOne(mappedBy = "homeDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private YourNeeds yourNeeds;
}
