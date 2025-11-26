package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDetailsRepository extends JpaRepository<PropertyDetails, Long> {
}
