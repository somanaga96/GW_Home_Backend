package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {

    Policy findByQuoteId(Long quoteId);
}
