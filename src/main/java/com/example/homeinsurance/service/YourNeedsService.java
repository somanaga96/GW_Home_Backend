package com.example.homeinsurance.service;

import com.example.homeinsurance.model.YourNeeds;
import com.example.homeinsurance.repository.YourNeedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YourNeedsService {
    @Autowired
    private final YourNeedsRepository repo;

    public YourNeedsService(YourNeedsRepository repo) {
        this.repo = repo;
    }

    public YourNeeds save(YourNeeds data) {
        return repo.save(data);
    }

    public YourNeeds get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
