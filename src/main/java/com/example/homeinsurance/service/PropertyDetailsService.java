package com.example.homeinsurance.service;

import com.example.homeinsurance.model.home.PropertyDetails;
import com.example.homeinsurance.repository.PropertyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyDetailsService {
    @Autowired
    private final PropertyDetailsRepository repo;

    public PropertyDetailsService(PropertyDetailsRepository repo) {
        this.repo = repo;
    }

    public PropertyDetails save(PropertyDetails p) {
        return repo.save(p);
    }

    public List<PropertyDetails> findAll() {
        return repo.findAll();
    }

    public PropertyDetails findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
