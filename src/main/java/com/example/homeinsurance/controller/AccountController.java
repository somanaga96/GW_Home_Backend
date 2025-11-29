package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {
    @Autowired
    private final AccountService service;

    @PostMapping
    public Account create(@RequestBody Account a) {
        return service.save(a);
    }

    @GetMapping
    public List<Account> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Account one(@PathVariable Long id) {
        return service.get(id);
    }

//    @GetMapping("/policies/{id}")
//    public List<Policy> getAllPoliciesForAccount(@PathVariable Long id) {
//        return service.getAllPoliciesForAccount(id);
//    }
}
