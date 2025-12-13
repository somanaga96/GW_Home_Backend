package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.QuoteDTO;
import com.example.homeinsurance.entity.HomeDetails;
import com.example.homeinsurance.entity.Quote;
import com.example.homeinsurance.entity.Submission;
import com.example.homeinsurance.repository.QuoteRepository;
import com.example.homeinsurance.repository.SubmissionRepository;
import com.example.homeinsurance.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final SubmissionRepository submissionRepository;
    private final QuoteRepository quoteRepository;

    @Override
    public QuoteDTO createQuote(String submissionNumber) {

        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        // ❌ Prevent duplicate quote
        if (submission.getQuote() != null) {
            throw new RuntimeException("Quote already exists for submission");
        }

        // ❌ Risk must exist
        HomeDetails home = submission.getHomeDetails();
        if (home == null) {
            throw new RuntimeException("Home details required before quote");
        }

        // ⭐ Mock Rating Logic (Simple but GW-style)
        double basePremium = calculateBasePremium(home);
        double claimLoading = calculateClaimLoading(submission);
        double totalPremium = basePremium + claimLoading;

        Quote quote = Quote.builder()
                .quoteNumber("QUO-" + UUID.randomUUID())
                .basePremium(basePremium)
                .claimLoading(claimLoading)
                .totalPremium(totalPremium)
                .submission(submission)

                .build();

        submission.setStatus("QUOTED");
        submission.setQuote(quote);

        quoteRepository.save(quote);

        return toDTO(quote);
    }

    @Override
    public QuoteDTO getQuoteBySubmission(String submissionNumber) {
        Quote quote = quoteRepository
                .findBySubmission_SubmissionNumber(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        return toDTO(quote);
    }

    // ---------------- Rating Logic ----------------

    private double calculateBasePremium(HomeDetails home) {
        int yearBuilt = home.getPropertyDetails().getBuiltYear();
        int rooms = home.getPropertyDetails().getBedRooms();

        double premium = 3000;
        premium += rooms * 500;

        if (yearBuilt < 2000) {
            premium += 2000;
        }

        return premium;
    }

    private double calculateClaimLoading(Submission submission) {
        int claimCount = submission.getClaims().size();
        return claimCount * 1000;
    }

    private QuoteDTO toDTO(Quote quote) {
        return QuoteDTO.builder()
                .quoteNumber(quote.getQuoteNumber())
                .basePremium(quote.getBasePremium())
                .claimLoading(quote.getClaimLoading())
                .totalPremium(quote.getTotalPremium())
                .submissionNumber(quote.getSubmission().getSubmissionNumber())
                .build();
    }
}
