package com.example.homeinsurance.service;

import com.example.homeinsurance.model.claims.ClaimsPageData;
import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.repository.QuoteRepository;
import com.example.homeinsurance.repository.claim_repository.ClaimsPageDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimsService {
    @Autowired
    private final QuoteRepository quoteRepo;
    private final ClaimsPageDataRepository claimsRepo;
    @Autowired
    public ClaimsPageData save(String quoteId, ClaimsPageData data) {

        Quote quote = quoteRepo.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Quote not found"));

        // Link children properly
        if (data.getClaims() != null) {
            data.getClaims().forEach(c -> c.setClaimsPage(data));
        }

        data.setQuote(quote);
        quote.setClaimsPage(data);

        // Save claims page
        return claimsRepo.save(data);
    }

    public ClaimsPageData get(Long id) {
        return claimsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Claims Page not found"));
    }
}

