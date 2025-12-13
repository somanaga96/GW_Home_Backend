package com.example.homeinsurance.service;


import com.example.homeinsurance.dto.SubmissionDTO;

import java.util.List;

public interface SubmissionService {

    SubmissionDTO createSubmission(Long accountId);

    SubmissionDTO getBySubmissionNumber(String submissionNumber);

    List<SubmissionDTO> getSubmissionsByAccount(Long accountId);
}

