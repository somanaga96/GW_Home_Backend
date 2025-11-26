package com.example.homeinsurance.repository.quote_repository;

import com.example.homeinsurance.model.quote.QuoteCoverRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteCoverRowRepository extends JpaRepository<QuoteCoverRow, Long> {
}
