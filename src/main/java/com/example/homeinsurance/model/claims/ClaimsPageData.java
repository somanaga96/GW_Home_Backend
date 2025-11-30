package com.example.homeinsurance.model.claims;

import com.example.homeinsurance.model.quote.Quote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "claim_page")
public class ClaimsPageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean anyClaimsIn5Years;
    private Boolean anyOutstandingClaims;
    private String claimFreeBuildingsYears;
    private String claimFreeContentsYears;

    // ---- Many claim items
    @OneToMany(mappedBy = "claimsPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClaimItem> claims;

    // ---- Link back to Quote
    @OneToOne
    @JoinColumn(name = "quote_id")
    @JsonBackReference("quote-claims")
    private Quote quote;
}
