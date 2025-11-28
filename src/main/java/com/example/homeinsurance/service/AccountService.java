package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    @Autowired
    private final AccountRepository repo;

    public Account save(Account a) { return repo.save(a); }

    public List<Account> all() { return repo.findAll(); }

    public Account get(Long id) {
        return repo.findById(id).orElse(null);
    }
}
