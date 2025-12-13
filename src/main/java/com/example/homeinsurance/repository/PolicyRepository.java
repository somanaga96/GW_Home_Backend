package com.example.homeinsurance.repository;

import com.example.homeinsurance.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    Optional<Policy> findByQuote_Submission_SubmissionNumber(String submissionNumber);
}
