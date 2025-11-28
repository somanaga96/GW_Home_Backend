package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PolicyService {
    @Autowired
    private final PolicyRepository repo;
    @Autowired
    private final QuoteService quoteService;

    public Policy createPolicy(Long quoteId) {
        Quote q = quoteService.get(quoteId);

        Policy p = new Policy();
        p.setPolicyNumber("POL-" + System.currentTimeMillis());
        p.setStartDate(LocalDate.now());
        p.setEndDate(LocalDate.now().plusYears(1));
        p.setStatus("ACTIVE");
        p.setQuote(q);
        p.setAccount(q.getSubmission().getAccount());

        return repo.save(p);
    }
}
