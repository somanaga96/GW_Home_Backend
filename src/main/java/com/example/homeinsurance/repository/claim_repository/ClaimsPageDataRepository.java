package com.example.homeinsurance.repository.claim_repository;

import com.example.homeinsurance.model.claims.ClaimsPageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsPageDataRepository extends JpaRepository<ClaimsPageData, Long> {
}
