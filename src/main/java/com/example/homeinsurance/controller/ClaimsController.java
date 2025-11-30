package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.claims.ClaimsPageData;
import com.example.homeinsurance.service.ClaimsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ClaimsController {
    @Autowired
    private final ClaimsService service;

    @PostMapping("/create/{quoteId}")
    public ClaimsPageData createClaimsPage(
            @PathVariable Long quoteId,
            @RequestBody ClaimsPageData claimsPageData
    ) {
        return service.save(quoteId, claimsPageData);
    }

    @GetMapping("/{id}")
    public ClaimsPageData getById(@PathVariable Long id) {
        return service.get(id);
    }
}

