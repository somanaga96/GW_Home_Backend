package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submission")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SubmissionController {
    @Autowired
    private final SubmissionService service;

    @PostMapping("/create/{accountId}")
    public Submission create(@PathVariable Long accountId) {
        return service.createSubmission(accountId);
    }

    @GetMapping("/{submission_id}")
    public Submission getSubmissionBySubmissionId(@PathVariable String submission_id) {
        return service.getSubmissionBySubmissionId(submission_id);
    }

    // ⭐ Get all submissions for an account
    @GetMapping("/account/{accountId}")
    public List<Submission> getAllSubmissionsForAccount(@PathVariable Long accountId) {
        return service.getAllSubmissionsForAccount(accountId);
    }
}


