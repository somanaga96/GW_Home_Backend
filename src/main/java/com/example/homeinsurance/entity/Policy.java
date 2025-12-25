package com.example.homeinsurance.entity;

import com.example.homeinsurance.enums.PaymentMethod;
import com.example.homeinsurance.enums.PaymentPlanType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "policies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String policyNumber;

    private LocalDate effectiveDate;
    private LocalDate expiryDate;

    private Double totalPremium;

    @Enumerated(EnumType.STRING)
    private PaymentPlanType paymentPlanType;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private Double depositAmount;
    private Integer installmentCount;
    private Double installmentAmount;
    private String status;

    // ⭐ One Policy → One Quote
    @OneToOne
    @JoinColumn(name = "quote_id", nullable = false, unique = true)
    private Quote quote;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private List<Cancellation> cancellations = new ArrayList<>();

    // ⭐ Many Policies → One Account
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


}
