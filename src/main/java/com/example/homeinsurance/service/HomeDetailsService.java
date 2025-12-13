package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.HomeDetailsDTO;

public interface HomeDetailsService {

    HomeDetailsDTO save(String submissionNumber, HomeDetailsDTO dto);

    HomeDetailsDTO getBySubmission(String submissionNumber);
}
