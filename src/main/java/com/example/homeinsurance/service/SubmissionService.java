package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.repository.AccountRepository;
import com.example.homeinsurance.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    @Autowired
    private final SubmissionRepository submissionRepo;
    @Autowired
    private final AccountRepository accountRepo;

    // Create submission for account
    public Submission createSubmission(Long accountId) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Submission s = new Submission();
        s.setAccount(acc);
        s.setStatus("DRAFT");
        s.setCreatedDate(LocalDate.now());
        s.setSubmissionNumber("SUB-" + System.currentTimeMillis());
        return submissionRepo.save(s);
    }

    public Submission get(Long id) {
        return submissionRepo.findById(id).orElseThrow();
    }

    public Submission getSubmissionBySubmissionId(String submissionId) {
        return submissionRepo.findBySubmissionNumberIgnoreCase(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission number not found: " + submissionId));
    }


    public List<Submission> getAllSubmissionsForAccount(Long accountId) {
        return submissionRepo.findByAccountId(accountId);
    }
}
