package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class QuoteController {
    @Autowired
    private final QuoteService service;

    // ⭐ Create Quote for a Submission
    @PostMapping("/create/{submissionId}")
    public Quote createQuote(@PathVariable String submissionId) {
        return service.createQuote(submissionId);
    }

    // ⭐ Get Quote by ID
    @GetMapping("/{id}")
    public Quote getQuote(@PathVariable Long id) {
        return service.getQuote(id);
    }

    // ⭐ List all quotes for a Submission
    @GetMapping("/submission/{submissionId}")
    public List<Quote> getQuotesForSubmission(@PathVariable Long submissionId) {
        return service.getQuotesBySubmissionId(submissionId);
    }
}
