package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO create(AccountDTO dto);

    AccountDTO getById(Long id);

    List<AccountDTO> getAll();

    AccountDTO update(Long id, AccountDTO dto);

    void delete(Long id);

    List<AccountDTO> searchAccounts(
            String brandName,
            String firstName,
            String lastName,
            String dob,
            String postcode
    );
}
