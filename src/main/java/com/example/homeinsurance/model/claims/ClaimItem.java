package com.example.homeinsurance.model.claims;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "claim_item")
public class ClaimItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimCause;
    private String cover;
    private String dateOfLoss;

    @Column(name = "claim_value")
    private Integer value;

    private Boolean sameAddress;

    // Many Items → One Claims Page
    @ManyToOne
    @JoinColumn(name = "claim_page_id")
    private ClaimsPageData claimsPage;
}
