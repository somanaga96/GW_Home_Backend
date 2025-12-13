package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.BindPolicyRequestDTO;
import com.example.homeinsurance.dto.PolicyDTO;
import com.example.homeinsurance.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService policyService;

    // ⭐ Bind Policy
    @PostMapping("/bind/{submissionNumber}")
    public PolicyDTO bindPolicy(
            @PathVariable String submissionNumber,
            @Valid @RequestBody BindPolicyRequestDTO request
    ) {
        return policyService.bindPolicy(submissionNumber, request);
    }
}
