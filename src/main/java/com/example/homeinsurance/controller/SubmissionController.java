package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.SubmissionDTO;
import com.example.homeinsurance.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SubmissionController {

    private final SubmissionService submissionService;

    // ⭐ Create Submission (GW Job Start)
    @PostMapping("/account/{accountId}")
    public SubmissionDTO create(@PathVariable Long accountId) {
        return submissionService.createSubmission(accountId);
    }

    // ⭐ Fetch by submission number
    @GetMapping("/{submissionNumber}")
    public SubmissionDTO getBySubmissionNumber(
            @PathVariable String submissionNumber
    ) {
        return submissionService.getBySubmissionNumber(submissionNumber);
    }

    // ⭐ Get all submissions for account
    @GetMapping("/account/{accountId}")
    public List<SubmissionDTO> getByAccount(@PathVariable Long accountId) {
        return submissionService.getSubmissionsByAccount(accountId);
    }
}
