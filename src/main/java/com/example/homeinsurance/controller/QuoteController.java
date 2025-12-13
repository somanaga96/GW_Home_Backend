package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.QuoteDTO;
import com.example.homeinsurance.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class QuoteController {

    private final QuoteService quoteService;

    // ⭐ Click "Quote" in UI
    @PostMapping("/submission/{submissionNumber}")
    public QuoteDTO createQuote(@PathVariable String submissionNumber) {
        return quoteService.createQuote(submissionNumber);
    }

    // ⭐ View Quote Summary
    @GetMapping("/submission/{submissionNumber}")
    public QuoteDTO getQuote(@PathVariable String submissionNumber) {
        return quoteService.getQuoteBySubmission(submissionNumber);
    }
}
