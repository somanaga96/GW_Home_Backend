package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.BindPolicyRequestDTO;
import com.example.homeinsurance.dto.PolicyDTO;
import com.example.homeinsurance.entity.Policy;
import com.example.homeinsurance.entity.Quote;
import com.example.homeinsurance.entity.Submission;
import com.example.homeinsurance.enums.PaymentMethod;
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

        // ---------- Load Submission ----------
        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        if (!"QUOTED".equalsIgnoreCase(submission.getStatus())) {
            throw new RuntimeException("Submission must be QUOTED before binding");
        }

        // ---------- Load Quote ----------
        Quote quote = submission.getQuote();
        if (quote == null) {
            throw new RuntimeException("Quote not found");
        }

        // ---------- Safe Enum Conversion ----------
        PaymentPlanType planType;
        try {
            planType = PaymentPlanType.valueOf(request.getPaymentPlanType());
        } catch (Exception e) {
            throw new RuntimeException(
                    "Invalid paymentPlanType: " + request.getPaymentPlanType()
            );
        }

        PaymentMethod paymentMethod;
        try {
            paymentMethod = PaymentMethod.valueOf(request.getPaymentMethod());
        } catch (Exception e) {
            throw new RuntimeException(
                    "Invalid paymentMethod: " + request.getPaymentMethod()
            );
        }

        // ---------- Create Policy ----------
        double totalPremium = quote.getTotalPremium();

        Policy policy = Policy.builder()
                .policyNumber("POL-" + UUID.randomUUID())
                .effectiveDate(LocalDate.now())
                .expiryDate(LocalDate.now().plusYears(1))
                .totalPremium(totalPremium)
                .paymentPlanType(planType)
                .paymentMethod(paymentMethod)
                .quote(quote)
                .status("ACTIVE")
                .build();

        // ---------- Apply Payment Plan ----------
        applyPaymentPlan(policy, totalPremium);

        // ---------- Update Submission ----------
        submission.setStatus("BOUND");
        submissionRepository.save(submission);

        // ---------- Save Policy ----------
        policyRepository.save(policy);

        return toDTO(policy);
    }

    @Override
    public PolicyDTO getPolicy(String policyNumber) {
        Policy policy = policyRepository.findByPolicyNumber(policyNumber)
                .orElseThrow(() -> new RuntimeException("Policy not found: " + policyNumber));

        return toDTO(policy);
    }


    // ================= PAYMENT LOGIC =================

    private void applyPaymentPlan(Policy policy, double totalPremium) {

        switch (policy.getPaymentPlanType()) {

            case SINGLE_PAYMENT -> {
                policy.setDepositAmount(totalPremium);
                policy.setInstallmentCount(0);
                policy.setInstallmentAmount(0.0);
            }

            case DEPOSIT_AND_INSTALLMENTS -> {
                double deposit = totalPremium * 0.25;
                double remaining = totalPremium - deposit;

                policy.setDepositAmount(deposit);
                policy.setInstallmentCount(11);
                policy.setInstallmentAmount(remaining / 11);
            }

            case FULL_INSTALLMENTS -> {
                policy.setDepositAmount(0.0);
                policy.setInstallmentCount(12);
                policy.setInstallmentAmount(totalPremium / 12);
            }
        }
    }

    // ================= DTO MAPPER =================

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
