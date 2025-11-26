package com.example.homeinsurance.model.claims;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "claimPage")
public class ClaimsPageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean anyClaimsIn5Years;
    private Boolean anyOutstandingClaims;
    private String claimFreeBuildingsYears;
    private String claimFreeContentsYears;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ClaimItem> claims;
}
