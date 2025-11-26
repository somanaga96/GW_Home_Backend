package com.example.homeinsurance.service;

import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    @Autowired
    private final QuoteRepository repo;
    public QuoteService(QuoteRepository repo){ this.repo = repo; }

    public Quote save(Quote q){ return repo.save(q); }
    public Quote findById(Long id){ return repo.findById(id).orElse(null); }
    public List<Quote> findAll(){ return repo.findAll(); }
}
