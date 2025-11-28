package com.example.homeinsurance.repository.claim_repository;

import com.example.homeinsurance.model.claims.ClaimItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimItemRepository extends JpaRepository<ClaimItem, Long> {
}
