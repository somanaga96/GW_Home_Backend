package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.reinstate.ReinstatePolicyRequestDTO;
import com.example.homeinsurance.dto.reinstate.ReinstatementDTO;
import com.example.homeinsurance.entity.Cancellation;
import com.example.homeinsurance.entity.Policy;
import com.example.homeinsurance.entity.Reinstatement;
import com.example.homeinsurance.repository.CancellationRepository;
import com.example.homeinsurance.repository.PolicyRepository;
import com.example.homeinsurance.repository.ReinstatementRepository;
import com.example.homeinsurance.service.ReinstatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReinstatementServiceImpl implements ReinstatementService {

    private final PolicyRepository policyRepository;
    private final CancellationRepository cancellationRepository;
    private final ReinstatementRepository reinstatementRepository;

    @Override
    public ReinstatementDTO reinstatePolicy(
            String policyNumber,
            ReinstatePolicyRequestDTO request
    ) {

        Policy policy = policyRepository
                .findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        // ✅ Must be cancelled
        if (!"CANCELLED".equalsIgnoreCase(policy.getStatus())) {
            throw new RuntimeException("Only CANCELLED policies can be reinstated");
        }

        Cancellation cancellation = cancellationRepository
                .findTopByPolicy_PolicyNumberOrderByCancelDateDesc(policyNumber)
                .orElseThrow(() -> new RuntimeException("Cancellation record not found"));


        // ✅ Date check
        if (request.getReinstateDate().isBefore(cancellation.getCancelDate())) {
            throw new RuntimeException("Reinstatement date cannot be before cancellation date");
        }

        // ❌ Prevent double reinstatement
        reinstatementRepository.findByPolicy_PolicyNumber(policyNumber)
                .ifPresent(r -> {
                    throw new RuntimeException("Policy already reinstated");
                });

        Reinstatement reinstatement = Reinstatement.builder()
                .policy(policy)
                .reinstateDate(request.getReinstateDate())
                .reason(request.getReason())
                .build();

        policy.setStatus("ACTIVE");

        reinstatementRepository.save(reinstatement);
        policyRepository.save(policy);

        return toDTO(reinstatement);
    }

    private ReinstatementDTO toDTO(Reinstatement reinstatement) {
        return ReinstatementDTO.builder()
                .policyNumber(reinstatement.getPolicy().getPolicyNumber())
                .reinstateDate(reinstatement.getReinstateDate())
                .reason(reinstatement.getReason())
                .policyStatus(reinstatement.getPolicy().getStatus())
                .build();
    }
}
