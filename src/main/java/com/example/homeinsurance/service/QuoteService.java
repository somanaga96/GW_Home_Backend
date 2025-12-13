package com.example.homeinsurance.service;

import com.example.homeinsurance.dto.QuoteDTO;

public interface QuoteService {

    QuoteDTO createQuote(String submissionNumber);

    QuoteDTO getQuoteBySubmission(String submissionNumber);
}
