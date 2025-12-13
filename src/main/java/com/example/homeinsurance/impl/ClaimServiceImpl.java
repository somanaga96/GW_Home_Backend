package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.ClaimDTO;
import com.example.homeinsurance.entity.Claim;
import com.example.homeinsurance.entity.Submission;
import com.example.homeinsurance.repository.ClaimRepository;
import com.example.homeinsurance.repository.SubmissionRepository;
import com.example.homeinsurance.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final SubmissionRepository submissionRepository;

    @Override
    public ClaimDTO addClaim(String submissionNumber, ClaimDTO dto) {

        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        Claim claim = Claim.builder()
                .anyClaimsIn5Years(dto.getAnyClaimsIn5Years())
                .anyOutstandingClaims(dto.getAnyOutstandingClaims())
                .claimFreeBuildingsYears(dto.getClaimFreeBuildingsYears())
                .claimFreeContentsYears(dto.getClaimFreeContentsYears())
                .submission(submission)
                .build();

        return toDTO(claimRepository.save(claim));
    }

    @Override
    public List<ClaimDTO> getClaimsBySubmission(String submissionNumber) {

        return claimRepository.findBySubmission_SubmissionNumber(submissionNumber)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void deleteClaim(Long claimId) {
        claimRepository.deleteById(claimId);
    }

    private ClaimDTO toDTO(Claim claim) {
        return ClaimDTO.builder()
                .id(claim.getId())
                .anyClaimsIn5Years(claim.getAnyClaimsIn5Years())
                .anyOutstandingClaims(claim.getAnyOutstandingClaims())
                .claimFreeBuildingsYears(claim.getClaimFreeBuildingsYears())
                .claimFreeContentsYears(claim.getClaimFreeContentsYears())
                .submissionNumber(claim.getSubmission().getSubmissionNumber())
                .build();
    }
}
