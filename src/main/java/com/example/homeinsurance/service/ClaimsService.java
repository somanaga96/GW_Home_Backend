package com.example.homeinsurance.service;

import com.example.homeinsurance.model.claims.ClaimsPageData;
import com.example.homeinsurance.repository.claim_repository.ClaimsPageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimsService {
    @Autowired
    private final ClaimsPageDataRepository repo;

    public ClaimsService(ClaimsPageDataRepository repo) {
        this.repo = repo;
    }

    public ClaimsPageData save(ClaimsPageData data) {
        return repo.save(data);
    }

    public ClaimsPageData get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
