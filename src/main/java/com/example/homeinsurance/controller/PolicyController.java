package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PolicyController {

    private final PolicyService service;

    // ⭐ Create a policy from a quote
    @PostMapping("/create/{quoteId}")
    public Policy createPolicy(@PathVariable String quoteId) {
        return service.createPolicy(quoteId);
    }

    // ⭐ Get a policy by ID
    @GetMapping("/{id}")
    public Policy getPolicy(@PathVariable Long id) {
        return service.getPolicy(id);
    }

    // ⭐ Find a policy from quote
    @GetMapping("/from-quote/{quoteId}")
    public Policy getPolicyFromQuote(@PathVariable Long quoteId) {
        return service.getPolicyByQuoteId(quoteId);
    }
}
