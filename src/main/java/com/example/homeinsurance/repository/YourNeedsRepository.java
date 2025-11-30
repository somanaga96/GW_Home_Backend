package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.home.YourNeeds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YourNeedsRepository extends JpaRepository<YourNeeds, Long> {
}

