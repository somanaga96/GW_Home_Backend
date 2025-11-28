package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SubmissionService {
    @Autowired
    private final SubmissionRepository repo;
    @Autowired
    private final AccountService accountService;

    public Submission createSubmission(Long accountId) {
        Account account = accountService.get(accountId);

        Submission s = new Submission();
        s.setSubmissionNumber("SUB-" + System.currentTimeMillis());
        s.setCreatedDate(LocalDate.now());
        s.setStatus("DRAFT");
        s.setAccount(account);

        return repo.save(s);
    }

    public Submission get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
