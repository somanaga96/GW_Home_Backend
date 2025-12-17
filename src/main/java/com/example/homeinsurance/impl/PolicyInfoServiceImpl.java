package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.PolicyInfoDTO;
import com.example.homeinsurance.entity.Submission;
import com.example.homeinsurance.repository.SubmissionRepository;
import com.example.homeinsurance.service.PolicyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyInfoServiceImpl implements PolicyInfoService {

    private final SubmissionRepository submissionRepository;

    @Override
    public PolicyInfoDTO save(String submissionNumber, PolicyInfoDTO dto) {

        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setCoverStartDate(dto.getCoverStartDate());
        submission.setPromoName(dto.getPromoName());
        submission.setPromoOverride(dto.getPromoOverride());
        submission.setCrossSell(dto.getCrossSell());
        submission.setOffering(dto.getOffering());

        submissionRepository.save(submission);

        return dto;
    }

    @Override
    public PolicyInfoDTO get(String submissionNumber) {

        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        return PolicyInfoDTO.builder()
                .coverStartDate(submission.getCoverStartDate())
                .promoName(submission.getPromoName())
                .promoOverride(submission.getPromoOverride())
                .crossSell(submission.getCrossSell())
                .offering(submission.getOffering())
                .build();
    }
}
