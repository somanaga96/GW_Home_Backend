package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.model.home.HomeDetails;
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
    public Quote createQuote(String submissionId, HomeDetails homeDetails) {

        Submission submission = submissionRepo.findBySubmissionNumberIgnoreCase(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission number not found: " + submissionId));

        Account acc = submission.getAccount();

        Quote quote = Quote.builder()
                .quoteNumber("QUO-" + UUID.randomUUID().toString().substring(0, 8))
                .createdDate(LocalDate.now())
                .status("Draft")
                .submission(submission)
                .account(acc)
                .build();

        // ⭐ Set back reference
        homeDetails.setQuote(quote);

        // ⭐ Set nested relations
        if (homeDetails.getPropertyDetails() != null) {
            // propertyDetails belongs to homeDetails
        }
        if (homeDetails.getYourNeeds() != null) {
            // yourNeeds belongs to homeDetails
        }

        quote.setHomeDetails(homeDetails);

        return quoteRepo.save(quote);   // ⭐ cascade saves homeDetails + nested entities
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
