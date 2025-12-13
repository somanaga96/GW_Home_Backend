package com.example.homeinsurance.controller;

import com.example.homeinsurance.dto.HomeDetailsDTO;
import com.example.homeinsurance.service.HomeDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home-details")
@RequiredArgsConstructor
public class HomeDetailsController {

    private final HomeDetailsService homeDetailsService;

    @PostMapping("/submission/{submissionNumber}")
    public HomeDetailsDTO save(
            @PathVariable String submissionNumber,
            @Valid @RequestBody HomeDetailsDTO dto
    ) {
        return homeDetailsService.save(submissionNumber, dto);
    }

    @GetMapping("/submission/{submissionNumber}")
    public HomeDetailsDTO get(
            @PathVariable String submissionNumber
    ) {
        return homeDetailsService.getBySubmission(submissionNumber);
    }
}
