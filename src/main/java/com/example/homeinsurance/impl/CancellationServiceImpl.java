package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.cancel.CancelPolicyRequestDTO;
import com.example.homeinsurance.dto.cancel.CancellationDTO;
import com.example.homeinsurance.entity.Cancellation;
import com.example.homeinsurance.entity.Policy;
import com.example.homeinsurance.repository.CancellationRepository;
import com.example.homeinsurance.repository.PolicyRepository;
import com.example.homeinsurance.service.CancellationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancellationServiceImpl implements CancellationService {

    private final PolicyRepository policyRepository;
    private final CancellationRepository cancellationRepository;

    @Override
    public CancellationDTO cancelPolicy(
            String policyNumber,
            CancelPolicyRequestDTO request
    ) {

        Policy policy = policyRepository
                .findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        // ✅ ONLY status check (GW correct)
        if (!"ACTIVE".equalsIgnoreCase(policy.getStatus())) {
            throw new RuntimeException("Only ACTIVE policies can be cancelled");
        }

        double refundAmount = calculateRefund(policy, request);

        Cancellation cancellation = Cancellation.builder()
                .policy(policy)
                .cancelDate(request.getCancelDate())
                .reason(request.getReason())
                .cancelType(request.getCancelType())
                .refundAmount(refundAmount)
                .build();

        policy.setStatus("CANCELLED");

        cancellationRepository.save(cancellation);
        policyRepository.save(policy);

        return toDTO(cancellation);
    }


    // ---------------- Helper Methods ----------------

    private double calculateRefund(Policy policy, CancelPolicyRequestDTO request) {

        if ("FLAT".equalsIgnoreCase(request.getCancelType())) {
            return policy.getTotalPremium();
        }

        if ("PRO_RATA".equalsIgnoreCase(request.getCancelType())) {
            return policy.getTotalPremium() * 0.5; // mock logic
        }

        return 0.0;
    }

    private CancellationDTO toDTO(Cancellation cancellation) {
        return CancellationDTO.builder()
                .policyNumber(cancellation.getPolicy().getPolicyNumber())
                .cancelDate(cancellation.getCancelDate())
                .reason(cancellation.getReason())
                .cancelType(cancellation.getCancelType())
                .refundAmount(cancellation.getRefundAmount())
                .policyStatus(cancellation.getPolicy().getStatus())
                .build();
    }
}
