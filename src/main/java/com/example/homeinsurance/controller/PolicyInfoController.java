package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.PolicyInfoDTO;
import com.example.homeinsurance.service.PolicyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PolicyInfoController {

    private final PolicyInfoService policyInfoService;

    @PostMapping("/{submissionNumber}/policy-info")
    public PolicyInfoDTO save(
            @PathVariable String submissionNumber,
            @RequestBody PolicyInfoDTO dto
    ) {
        return policyInfoService.save(submissionNumber, dto);
    }

    @GetMapping("/{submissionNumber}/policy-info")
    public PolicyInfoDTO get(
            @PathVariable String submissionNumber
    ) {
        return policyInfoService.get(submissionNumber);
    }
}
