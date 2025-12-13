package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reinstatements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reinstatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reinstateDate;
    private String reason; // PAYMENT_RECEIVED, ADMIN_ERROR

    // ⭐ One Reinstatement → One Policy
    @OneToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;
}
