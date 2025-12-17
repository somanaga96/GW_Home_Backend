package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.AccountDTO;
import com.example.homeinsurance.entity.Account;
import com.example.homeinsurance.repository.AccountRepository;
import com.example.homeinsurance.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO create(AccountDTO dto) {

        if (accountRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }

        Account account = toEntity(dto);
        Account saved = accountRepository.save(account);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDTO getById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        return toDTO(account);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public AccountDTO update(Long id, AccountDTO dto) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));

        account.setBrandName(dto.getBrandName());
        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        account.setDateOfBirth(dto.getDateOfBirth());
        account.setPostcode(dto.getPostcode());
        account.setEmail(dto.getEmail());

        return toDTO(accountRepository.save(account));
    }

    @Override
    public void delete(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDTO> searchAccounts(
            String brandName,
            String firstName,
            String lastName,
            String dob,
            String postcode
    ) {
        return accountRepository.searchAccounts(
                        emptyToNull(brandName),
                        emptyToNull(firstName),
                        emptyToNull(lastName),
                        emptyToNull(dob),
                        emptyToNull(postcode)
                )
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ================== MAPPERS ==================

    private Account toEntity(AccountDTO dto) {
        return Account.builder()
                .brandName(dto.getBrandName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .postcode(dto.getPostcode())
                .email(dto.getEmail())
                .build();
    }

    private AccountDTO toDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .brandName(account.getBrandName())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .dateOfBirth(account.getDateOfBirth())
                .postcode(account.getPostcode())
                .email(account.getEmail())
                .build();
    }

    private String emptyToNull(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }
}
