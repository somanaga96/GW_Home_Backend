package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.BindPolicyRequestDTO;
import com.example.homeinsurance.dto.cancel.CancelPolicyRequestDTO;
import com.example.homeinsurance.dto.PolicyDTO;

public interface PolicyService {

    PolicyDTO bindPolicy(String submissionNumber, BindPolicyRequestDTO request);

    PolicyDTO getPolicy(String policyNumber);
}
