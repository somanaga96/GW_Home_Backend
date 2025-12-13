package com.example.homeinsurance.repository;

import com.example.homeinsurance.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findBySubmission_SubmissionNumber(String submissionNumber);
}
