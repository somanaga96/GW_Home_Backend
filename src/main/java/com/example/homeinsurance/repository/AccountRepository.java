package com.example.homeinsurance.repository;

import com.example.homeinsurance.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE " +
            "(:brand IS NULL OR a.brandName = :brand) AND " +
            "(:fname IS NULL OR LOWER(a.firstName) LIKE LOWER(CONCAT('%',:fname,'%'))) AND " +
            "(:lname IS NULL OR LOWER(a.lastName) LIKE LOWER(CONCAT('%',:lname,'%'))) AND " +
            "(:dob IS NULL OR a.dateOfBirth = :dob) AND " +
            "(:postcode IS NULL OR a.postcode = :postcode) AND " +
            "(:quote IS NULL OR a.quoteRefId = :quote)")
    List<Account> search(
            @Param("brand") String brand,
            @Param("fname") String firstName,
            @Param("lname") String lastName,
            @Param("dob") LocalDate dob,
            @Param("postcode") String postcode,
            @Param("quote") String quoteRef
    );
}
