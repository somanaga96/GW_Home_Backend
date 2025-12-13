package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.ClaimDTO;
import com.example.homeinsurance.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService claimService;

    // ⭐ Add claim to submission (Claims Screen)
    @PostMapping("/submission/{submissionNumber}")
    public ClaimDTO addClaim(
            @PathVariable String submissionNumber,
            @Valid @RequestBody ClaimDTO dto
    ) {
        return claimService.addClaim(submissionNumber, dto);
    }

    // ⭐ View all claims for submission
    @GetMapping("/submission/{submissionNumber}")
    public List<ClaimDTO> getClaims(
            @PathVariable String submissionNumber
    ) {
        return claimService.getClaimsBySubmission(submissionNumber);
    }

    // ⭐ Remove claim (GW allows delete before quote)
    @DeleteMapping("/{claimId}")
    public void deleteClaim(@PathVariable Long claimId) {
        claimService.deleteClaim(claimId);
    }
}
