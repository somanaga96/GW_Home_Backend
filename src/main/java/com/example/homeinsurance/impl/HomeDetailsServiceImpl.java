package com.example.homeinsurance.impl;

import com.example.homeinsurance.dto.HomeDetailsDTO;
import com.example.homeinsurance.dto.PropertyDetailsDTO;
import com.example.homeinsurance.dto.YourNeedsDTO;
import com.example.homeinsurance.entity.HomeDetails;
import com.example.homeinsurance.entity.PropertyDetails;
import com.example.homeinsurance.entity.Submission;
import com.example.homeinsurance.entity.YourNeeds;
import com.example.homeinsurance.repository.HomeDetailsRepository;
import com.example.homeinsurance.repository.SubmissionRepository;
import com.example.homeinsurance.service.HomeDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeDetailsServiceImpl implements HomeDetailsService {

    private final SubmissionRepository submissionRepository;
    private final HomeDetailsRepository homeDetailsRepository;

    @Override
    public HomeDetailsDTO save(String submissionNumber, HomeDetailsDTO dto) {

        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        HomeDetails homeDetails = HomeDetails.builder()
                .submission(submission)
                .build();

        // Property
        PropertyDetails property = PropertyDetails.builder()
                .riskAddress(dto.getPropertyDetails().getRiskAddress())
                .homeType(dto.getPropertyDetails().getHomeType())
                .builtYear(dto.getPropertyDetails().getBuiltYear())
                .bedRooms(dto.getPropertyDetails().getBedRooms())
                .hasSecurityAlarm(dto.getPropertyDetails().getHasSecurityAlarm())
                .homeDetails(homeDetails)
                .build();

        // Your Needs
        YourNeeds needs = YourNeeds.builder()
                .contentsCoverAmount(dto.getYourNeeds().getContentsCoverAmount())
                .buildingsSumInsured(dto.getYourNeeds().getBuildingsSumInsured())
                .contentsSumInsured(dto.getYourNeeds().getContentsSumInsured())
                .bikesOver500(dto.getYourNeeds().getBikesOver500())
                .highRiskItemsOver2000(dto.getYourNeeds().getHighRiskItemsOver2000())
                .homeDetails(homeDetails)
                .build();

        homeDetails.setPropertyDetails(property);
        homeDetails.setYourNeeds(needs);

        homeDetailsRepository.save(homeDetails);

        submission.setStatus("IN_PROGRESS");

        return dto;
    }

    @Override
    public HomeDetailsDTO getBySubmission(String submissionNumber) {
        Submission submission = submissionRepository
                .findBySubmissionNumberIgnoreCase(submissionNumber)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        HomeDetails home = submission.getHomeDetails();

        return HomeDetailsDTO.builder()
                .propertyDetails(PropertyDetailsDTO.builder()
                        .riskAddress(home.getPropertyDetails().getRiskAddress())
                        .hasSecurityAlarm(home.getPropertyDetails().getHasSecurityAlarm())
                        .builtYear(home.getPropertyDetails().getBuiltYear())
                        .bedRooms(home.getPropertyDetails().getBedRooms())
                        .hasSecurityAlarm(home.getPropertyDetails().getHasSecurityAlarm())
                        .build())
                .yourNeeds(YourNeedsDTO.builder()
                        .contentsCoverAmount(home.getYourNeeds().getContentsCoverAmount())
                        .buildingsSumInsured(home.getYourNeeds().getBuildingsSumInsured())
                        .contentsSumInsured(home.getYourNeeds().getContentsSumInsured())
                        .bikesOver500(home.getYourNeeds().getBikesOver500())
                        .highRiskItemsOver2000(home.getYourNeeds().getHighRiskItemsOver2000())
                        .build())
                .build();
    }
}
