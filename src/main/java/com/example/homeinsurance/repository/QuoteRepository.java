package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
