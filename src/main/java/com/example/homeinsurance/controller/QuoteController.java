package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class QuoteController {
    @Autowired
    private final QuoteService service;

    @PostMapping("/generate/{submissionId}")
    public Quote generate(@PathVariable Long submissionId, @RequestParam double premium) {
        return service.generateQuote(submissionId, premium);
    }

    @GetMapping("/{id}")
    public Quote get(@PathVariable Long id) {
        return service.get(id);
    }
}
