package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.reinstate.ReinstatePolicyRequestDTO;
import com.example.homeinsurance.dto.reinstate.ReinstatementDTO;

public interface ReinstatementService {

    ReinstatementDTO reinstatePolicy(
            String policyNumber,
            ReinstatePolicyRequestDTO request
    );
}
