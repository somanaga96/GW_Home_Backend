package com.example.homeinsurance.impl;


import com.example.homeinsurance.dto.SubmissionDTO;
import com.example.homeinsurance.entity.Account;
import com.example.homeinsurance.entity.Submission;
import com.example.homeinsurance.repository.AccountRepository;
import com.example.homeinsurance.repository.SubmissionRepository;
import com.example.homeinsurance.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AccountRepository accountRepository;

    @Override
    public SubmissionDTO createSubmission(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Submission submission = Submission.builder()
                .submissionNumber("SUB-" + UUID.randomUUID())
                .status("DRAFT")
                .createdDate(LocalDate.now())
                .account(account)
                .build();

        return toSubmissionDTO(submissionRepository.save(submission));
    }

    @Override
    public SubmissionDTO getBySubmissionNumber(String submissionNumber) {
        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        return toSubmissionDTO(submission);
    }

    @Override
    public List<SubmissionDTO> getSubmissionsByAccount(Long accountId) {
        return submissionRepository.findAll().stream()
                .filter(s -> s.getAccount().getId().equals(accountId))
                .map(this::toSubmissionDTO)
                .toList();
    }

    private SubmissionDTO toSubmissionDTO(Submission submission) {
        return SubmissionDTO.builder()
                .id(submission.getId())
                .submissionNumber(submission.getSubmissionNumber())
                .accountId(submission.getAccount().getId())
                .status(submission.getStatus())
                .createdDate(submission.getCreatedDate())
                .build();
    }
}
