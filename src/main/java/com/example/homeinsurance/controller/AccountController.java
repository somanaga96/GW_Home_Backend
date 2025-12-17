package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.AccountDTO;
import com.example.homeinsurance.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AccountController {

    private final AccountService accountService;

    // ✅ Create Account
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@Valid @RequestBody AccountDTO dto) {
        return accountService.create(dto);
    }

    // ✅ Get Account by ID
    @GetMapping("/{id}")
    public AccountDTO getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    // ✅ Get All Accounts
    @GetMapping
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }

    // ✅ GW-Style Search
    @GetMapping("/search")
    public List<AccountDTO> searchAccounts(
            @RequestParam(required = false) String brandName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String dob,
            @RequestParam(required = false) String postcode
    ) {
        return accountService.searchAccounts(
                brandName, firstName, lastName, dob, postcode
        );
    }

    // ✅ Update Account
    @PutMapping("/{id}")
    public AccountDTO update(
            @PathVariable Long id,
            @Valid @RequestBody AccountDTO dto
    ) {
        return accountService.update(id, dto);
    }

    // ✅ Delete Account
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
