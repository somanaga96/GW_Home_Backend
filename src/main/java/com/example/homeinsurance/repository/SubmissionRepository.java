package com.example.homeinsurance.repository;


import com.example.homeinsurance.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    Optional<Submission> findBySubmissionNumberIgnoreCase(String submissionNumber);
}
