package com.example.homeinsurance.service;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public List<Account> search() {
        return repo.findAll();
    }

    public Account save(Account account) {
        return repo.save(account);
    }

    public Account getById(Long id) {
        return repo.getById(id);
    }
}
