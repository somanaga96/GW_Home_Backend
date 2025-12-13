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
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@Valid @RequestBody AccountDTO dto) {
        return accountService.create(dto);
    }

    @GetMapping("/{id}")
    public AccountDTO getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @GetMapping
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }

    @PutMapping("/{id}")
    public AccountDTO update(
            @PathVariable Long id, @Valid
            @RequestBody AccountDTO dto
    ) {
        return accountService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
