package com.example.homeinsurance.repository;

import com.example.homeinsurance.entity.HomeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeDetailsRepository extends JpaRepository<HomeDetails, Long> {
}
