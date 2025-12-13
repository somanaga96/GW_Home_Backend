package com.example.homeinsurance.repository;

import com.example.homeinsurance.entity.Cancellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CancellationRepository extends JpaRepository<Cancellation, Long> {

    Optional<Cancellation> findByPolicy_PolicyNumber(String policyNumber);

    Optional<Cancellation> findTopByPolicy_PolicyNumberOrderByCancelDateDesc(String policyNumber);
}
