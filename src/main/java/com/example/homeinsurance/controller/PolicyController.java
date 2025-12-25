package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.BindPolicyRequestDTO;
import com.example.homeinsurance.dto.SubmissionDTO;
import com.example.homeinsurance.dto.cancel.CancelPolicyRequestDTO;
import com.example.homeinsurance.dto.PolicyDTO;
import com.example.homeinsurance.dto.cancel.CancellationDTO;
import com.example.homeinsurance.dto.reinstate.ReinstatePolicyRequestDTO;
import com.example.homeinsurance.dto.reinstate.ReinstatementDTO;
import com.example.homeinsurance.service.CancellationService;
import com.example.homeinsurance.service.PolicyService;
import com.example.homeinsurance.service.ReinstatementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PolicyController {

    private final PolicyService policyService;
    private final CancellationService cancellationService;
    private final ReinstatementService reinstatementService;

    // ⭐ Bind Policy
    @PostMapping("/bind/{submissionNumber}")
    public PolicyDTO bindPolicy(
            @PathVariable String submissionNumber,
            @Valid @RequestBody BindPolicyRequestDTO request
    ) {
        return policyService.bindPolicy(submissionNumber, request);
    }

    // ⭐ Cancel Policy
    @PostMapping("/cancel/{policyNumber}")
    public CancellationDTO cancelPolicy(
            @PathVariable String policyNumber,
            @Valid @RequestBody CancelPolicyRequestDTO request
    ) {
        return cancellationService.cancelPolicy(policyNumber, request);
    }

    // ⭐ Reinstate Policy
    @PostMapping("/reinstate/{policyNumber}")
    public ReinstatementDTO reinstatePolicy(
            @PathVariable String policyNumber,
            @Valid @RequestBody ReinstatePolicyRequestDTO request
    ) {
        return reinstatementService.reinstatePolicy(policyNumber, request);
    }

    @GetMapping("/{policyNumber}")
    public PolicyDTO getPolicy(@PathVariable String policyNumber) {
        return policyService.getPolicy(policyNumber);
    }
    // ⭐ Get all submissions for account
    @GetMapping("/account/{accountId}")
    public List<PolicyDTO> getByAccount(@PathVariable Long accountId) {
        return policyService.getPoliciesByAccount(accountId);
    }

}
