package com.example.homeinsurance.repository;

import com.example.homeinsurance.entity.Reinstatement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReinstatementRepository extends JpaRepository<Reinstatement, Long> {

    Optional<Reinstatement> findByPolicy_PolicyNumber(String policyNumber);
}
