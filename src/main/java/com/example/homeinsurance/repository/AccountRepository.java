package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // flexible search across fields; any param may be null
    @Query("SELECT a FROM Account a WHERE " +
            "(:brandName IS NULL OR a.brandName = :brandName) AND " +
            "(:firstName IS NULL OR LOWER(a.firstName) LIKE LOWER(CONCAT('%',:firstName,'%'))) AND " +
            "(:lastName IS NULL OR LOWER(a.lastName) LIKE LOWER(CONCAT('%',:lastName,'%'))) AND " +
            "(:dob IS NULL OR a.dateOfBirth = :dob) AND " +
            "(:postcode IS NULL OR LOWER(a.postcode) = LOWER(:postcode)) AND " +
            "(:quoteRef IS NULL OR LOWER(a.quoteRefId) = LOWER(:quoteRef))")
    List<Account> search(
            @Param("brandName") String brandName,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("dob") LocalDate dob,
            @Param("postcode") String postcode,
            @Param("quoteRef") String quoteRef
    );
}
