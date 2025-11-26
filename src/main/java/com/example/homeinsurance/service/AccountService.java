package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public List<Account> search(String brandName, String firstName, String lastName,
                                LocalDate dob, String postcode, String quoteRef) {
        return repo.search(
                (brandName == null || brandName.isBlank()) ? null : brandName,
                (firstName == null || firstName.isBlank()) ? null : firstName,
                (lastName == null || lastName.isBlank()) ? null : lastName,
                dob,
                (postcode == null || postcode.isBlank()) ? null : postcode,
                (quoteRef == null || quoteRef.isBlank()) ? null : quoteRef
        );
    }

    public Account save(Account account) {
        return repo.save(account);
    }

    public Account getById(Long id) {
        return repo.getById(id);
    }
}
