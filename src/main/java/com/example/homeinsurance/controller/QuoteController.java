package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.quote.Quote;
import com.example.homeinsurance.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
@CrossOrigin(origins = "http://localhost:5173")
public class QuoteController {
    @Autowired
    private final QuoteService svc;
    public QuoteController(QuoteService svc){ this.svc = svc; }

    @GetMapping
    public List<Quote> all(){ return svc.findAll(); }

    @GetMapping("/{id}")
    public Quote one(@PathVariable Long id){ return svc.findById(id); }

    @PostMapping
    public Quote create(@RequestBody Quote q){ return svc.save(q); }

    @PutMapping("/{id}")
    public Quote update(@PathVariable Long id, @RequestBody Quote q){
        q.setId(id);
        return svc.save(q);
    }
}
