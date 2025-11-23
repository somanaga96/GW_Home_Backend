package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.Account;
import com.example.homeinsurance.repository.AccountRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {

    private final AccountRepository repo;

    public AccountController(AccountRepository repo) {
        this.repo = repo;
    }

    // CREATE account from frontend
    @PostMapping
    public Account create(@RequestBody Account account) {
        return repo.save(account);
    }

    // SEARCH accounts
    @GetMapping
    public List<Account> search(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
            @RequestParam(required = false) String postcode,
            @RequestParam(required = false) String quoteRef
    ) {
        return repo.search(
                brandName == null || brandName.isBlank() ? null : brandName,
                firstName == null || firstName.isBlank() ? null : firstName,
                lastName == null || lastName.isBlank() ? null : lastName,
                dob,
                postcode == null || postcode.isBlank() ? null : postcode,
                quoteRef == null || quoteRef.isBlank() ? null : quoteRef
        );
    }

    // GET account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getOne(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
