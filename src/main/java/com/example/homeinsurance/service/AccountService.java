package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.repository.AccountRepository;
import com.example.homeinsurance.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    @Autowired
    private final AccountRepository repo;
    @Autowired
    private final PolicyRepository policyRepository;

    public Account save(Account a) {
        return repo.save(a);
    }

    public List<Account> all() {
        return repo.findAll();
    }

    public Account get(Long id) {
        return repo.findById(id).orElse(null);
    }

    //    Submission submission=submissionRepo.findBySubmissionNumberIgnoreCase(submissionId)
//            .orElseThrow(() -> new RuntimeException("Submission number not found: " + submissionId));
//    public List<Policy> getAllPoliciesForAccount(Long account_id) {
//        Account account = repo.findById(account_id).orElseThrow(() -> new RuntimeException("Account not found :" + account_id));
//        policyRepository.findByAccountId(account_id);
//    }
}
