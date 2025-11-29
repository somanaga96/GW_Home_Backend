package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.repository.AccountRepository;
import com.example.homeinsurance.repository.PolicyRepository;
import com.example.homeinsurance.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.QAbstractAuditable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PolicyService {
    @Autowired
    private final PolicyRepository policyRepo;
    @Autowired
    private final QuoteRepository quoteRepo;
    @Autowired
    private final AccountRepository accountRepo;

    // ⭐ CREATE POLICY from Quote
    public Policy createPolicy(String quoteId) {

        Quote quote = quoteRepo.findByQuoteNumberIgnoreCase(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));


        Account acc = accountRepo.findById(quote.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Policy policy = Policy.builder()
                .policyNumber("POL-" + UUID.randomUUID().toString().substring(0, 8))
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .status("ACTIVE")
                .quote(quote)
                .account(acc)
                .build();

        return policyRepo.save(policy);
    }

    // ⭐ Get Policy by ID
    public Policy getPolicy(Long id) {
        return policyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found"));
    }

    // ⭐ Get Policy from Quote
    public Policy getPolicyByQuoteId(Long quoteId) {
        return policyRepo.findByQuoteId(quoteId);
    }
}
