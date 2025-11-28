package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PolicyController {
    @Autowired
    private final PolicyService service;

    @PostMapping("/bind/{quoteId}")
    public Policy bind(@PathVariable Long quoteId) {
        return service.createPolicy(quoteId);
    }

    @GetMapping("/{id}")
    public Policy get(@PathVariable Long id) {
        return service.createPolicy(id);
    }
}
