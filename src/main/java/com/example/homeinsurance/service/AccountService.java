package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    AccountDTO create(AccountDTO dto);

    AccountDTO getById(Long id);

    List<AccountDTO> getAll();

    AccountDTO update(Long id, AccountDTO dto);

    void delete(Long id);
}