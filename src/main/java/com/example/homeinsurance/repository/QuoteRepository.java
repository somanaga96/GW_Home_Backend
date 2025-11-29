package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.Submission;
import com.example.homeinsurance.model.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findBySubmissionId(Long submissionId);

    Optional<Quote> findByQuoteNumberIgnoreCase(String quoteId);
}
