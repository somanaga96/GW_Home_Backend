package com.example.homeinsurance.repository;

import com.example.homeinsurance.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmail(String email);

    @Query("""
        SELECT a FROM Account a
        WHERE (:brandName IS NULL OR a.brandName = :brandName)
          AND (:firstName IS NULL OR LOWER(a.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')))
          AND (:lastName IS NULL OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))
          AND (:dob IS NULL OR a.dateOfBirth = :dob)
          AND (:postcode IS NULL OR LOWER(a.postcode) LIKE LOWER(CONCAT('%', :postcode, '%')))
    """)
    List<Account> searchAccounts(
            @Param("brandName") String brandName,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("dob") String dob,
            @Param("postcode") String postcode
    );
}
