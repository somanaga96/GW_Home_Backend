package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {
    @Autowired
    private final PolicyRepository repo;
    public PolicyService(PolicyRepository repo){ this.repo = repo; }

    public Policy save(Policy p){ return repo.save(p); }
    public List<Policy> findAll(){ return repo.findAll(); }
    public Optional<Policy> findById(Long id){ return repo.findById(id); }
}
