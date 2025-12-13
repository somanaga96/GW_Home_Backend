package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.ClaimDTO;

import java.util.List;

public interface ClaimService {

    ClaimDTO addClaim(String submissionNumber, ClaimDTO dto);

    List<ClaimDTO> getClaimsBySubmission(String submissionNumber);

    void deleteClaim(Long claimId);
}
