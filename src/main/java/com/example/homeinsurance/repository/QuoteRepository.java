package com.example.homeinsurance.repository;

import com.example.homeinsurance.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Optional<Quote> findBySubmission_SubmissionNumber(String submissionNumber);
}
