package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.repository.AccountRepository;
import com.example.homeinsurance.repository.QuoteRepository;
import com.example.homeinsurance.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuoteService {
    @Autowired
    private final QuoteRepository quoteRepo;
    @Autowired
    private final SubmissionRepository submissionRepo;
    @Autowired
    private final AccountRepository accountRepo;

    // ⭐ CREATE QUOTE from Submission
    public Quote createQuote(String submissionId) {

        Submission submission=submissionRepo.findBySubmissionNumberIgnoreCase(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission number not found: " + submissionId));

        Account acc = accountRepo.findById(submission.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));


        Quote quote = Quote.builder()
                .quoteNumber("QUO-" + UUID.randomUUID().toString().substring(0, 8))
                .createdDate(LocalDate.now())
                .status("QUOTED")
                .submission(submission)
                .account(acc)
                .build();

        return quoteRepo.save(quote);
    }

    // ⭐ Get Quote By ID
    public Quote getQuote(Long id) {
        return quoteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
    }

    // ⭐ Get All Quotes for a Submission
    public List<Quote> getQuotesBySubmissionId(Long submissionId) {
        return quoteRepo.findBySubmissionId(submissionId);
    }
}
