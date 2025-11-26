package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173") // adjust your frontend URL
public class AccountController {
    @Autowired
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    // GET /api/accounts?brandName=&firstName=&lastName=&dob=yyyy-MM-dd&postcode=&quoteRef=
    @GetMapping
    public List<Account> search(

    ) {
        return service.search();
    }

    // optionally get single account
    @GetMapping("/{id}")
    public Account getOne(@PathVariable Long id) {
        return service.getById(id);
    }

    // create (for test / seed use)
    @PostMapping
    public Account create(@RequestBody Account account) {
        return service.save(account);
    }
}
