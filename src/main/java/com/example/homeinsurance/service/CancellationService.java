package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.cancel.CancelPolicyRequestDTO;
import com.example.homeinsurance.dto.cancel.CancellationDTO;

public interface CancellationService {

    CancellationDTO cancelPolicy(
            String policyNumber,
            CancelPolicyRequestDTO request
    );
}
