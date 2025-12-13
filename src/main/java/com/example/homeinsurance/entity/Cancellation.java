package com.example.homeinsurance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cancellations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate cancelDate;
    private String reason;
    private String cancelType;
    private Double refundAmount;

    // ✅ MANY cancellations for ONE policy
    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;
}
