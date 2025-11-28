package com.example.homeinsurance.service;

import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteService {
    @Autowired
    private final QuoteRepository repo;
    @Autowired
    private final SubmissionService submissionService;

    public Quote generateQuote(Long submissionId, Double premium) {
        Submission s = submissionService.get(submissionId);

        Quote q = new Quote();
        q.setQuoteNumber("QT-" + System.currentTimeMillis());
        q.setPremium(premium);
        q.setStatus("GENERATED");
        q.setSubmission(s);

        return repo.save(q);
    }

    public Quote get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
