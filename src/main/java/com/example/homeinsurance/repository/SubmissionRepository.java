package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    // All submissions for an account
    @Query("SELECT s FROM Submission s WHERE s.account.id = :accountId")
    List<Submission> findByAccountId(Long accountId);

    Optional<Submission> findBySubmissionNumberIgnoreCase(String submissionNumber);
}

