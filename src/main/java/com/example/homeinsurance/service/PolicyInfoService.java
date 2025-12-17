package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.PolicyInfoDTO;

public interface PolicyInfoService {

    PolicyInfoDTO save(String submissionNumber, PolicyInfoDTO dto);

    PolicyInfoDTO get(String submissionNumber);
}
