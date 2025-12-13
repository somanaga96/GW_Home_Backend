package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.BindPolicyRequestDTO;
import com.example.homeinsurance.dto.PolicyDTO;
import com.example.homeinsurance.entity.Policy;
import com.example.homeinsurance.entity.Quote;
import com.example.homeinsurance.entity.Submission;
import com.example.homeinsurance.enums.PaymentPlanType;
import com.example.homeinsurance.repository.PolicyRepository;
import com.example.homeinsurance.repository.SubmissionRepository;
import com.example.homeinsurance.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final SubmissionRepository submissionRepository;
    private final PolicyRepository policyRepository;

    @Override
    public PolicyDTO bindPolicy(String submissionNumber, BindPolicyRequestDTO request) {

        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        if (!"QUOTED".equals(submission.getStatus())) {
            throw new RuntimeException("Submission must be QUOTED before binding");
        }

        Quote quote = submission.getQuote();
        if (quote == null) {
            throw new RuntimeException("Quote not found");
        }

        double totalPremium = quote.getTotalPremium();

        Policy policy = Policy.builder()
                .policyNumber("POL-" + UUID.randomUUID())
                .effectiveDate(LocalDate.now())
                .expiryDate(LocalDate.now().plusYears(1))
                .totalPremium(totalPremium)
                .paymentPlanType(request.getPaymentPlanType())
                .paymentMethod(request.getPaymentMethod())
                .quote(quote)
                .build();

        applyPaymentPlan(policy, totalPremium);

        submission.setStatus("BOUND");

        policyRepository.save(policy);

        return toDTO(policy);
    }

    // ---------------- Payment Logic ----------------

    private void applyPaymentPlan(Policy policy, double totalPremium) {

        if (policy.getPaymentPlanType() == PaymentPlanType.SINGLE_PAYMENT) {
            policy.setDepositAmount(totalPremium);
            policy.setInstallmentCount(0);
            policy.setInstallmentAmount(0.0);
        }

        if (policy.getPaymentPlanType() == PaymentPlanType.DEPOSIT_AND_INSTALLMENTS) {
            double deposit = totalPremium * 0.25; // 25% deposit
            double remaining = totalPremium - deposit;

            policy.setDepositAmount(deposit);
            policy.setInstallmentCount(11);
            policy.setInstallmentAmount(remaining / 11);
        }

        if (policy.getPaymentPlanType() == PaymentPlanType.FULL_INSTALLMENTS) {
            policy.setDepositAmount(0.0);
            policy.setInstallmentCount(12);
            policy.setInstallmentAmount(totalPremium / 12);
        }
    }

    private PolicyDTO toDTO(Policy policy) {
        return PolicyDTO.builder()
                .policyNumber(policy.getPolicyNumber())
                .effectiveDate(policy.getEffectiveDate())
                .expiryDate(policy.getExpiryDate())
                .totalPremium(policy.getTotalPremium())
                .paymentPlanType(policy.getPaymentPlanType().name())
                .paymentMethod(policy.getPaymentMethod().name())
                .depositAmount(policy.getDepositAmount())
                .installmentCount(policy.getInstallmentCount())
                .installmentAmount(policy.getInstallmentAmount())
                .build();
    }
}
