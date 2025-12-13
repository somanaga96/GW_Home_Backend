package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.AccountDTO;
import com.example.homeinsurance.entity.Account;
import com.example.homeinsurance.repository.AccountRepository;
import com.example.homeinsurance.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO create(AccountDTO dto) {
        Account account = toEntity(dto);
        return toDTO(accountRepository.save(account));
    }

    @Override
    public AccountDTO getById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
        return toDTO(account);
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public AccountDTO update(Long id, AccountDTO dto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));

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
            throw new RuntimeException("Account not found: " + id);
        }
        accountRepository.deleteById(id);
    }

    // -------- Mapping methods --------

    private Account toEntity(AccountDTO dto) {
        return Account.builder()
                .id(dto.getId())
                .brandName(dto.getBrandName())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(dto.getDateOfBirth())
                .postcode(dto.getPostcode())
                .email(dto.getEmail())
                .build();
    }

    private AccountDTO toDTO(Account entity) {
        return AccountDTO.builder()
                .id(entity.getId())
                .brandName(entity.getBrandName())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .dateOfBirth(entity.getDateOfBirth())
                .postcode(entity.getPostcode())
                .email(entity.getEmail())
                .build();
    }
}

