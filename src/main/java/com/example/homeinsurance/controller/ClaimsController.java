package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.claims.ClaimsPageData;
import com.example.homeinsurance.service.ClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "http://localhost:5173")
public class ClaimsController {
@Autowired
    private final ClaimsService service;

    public ClaimsController(ClaimsService service) {
        this.service = service;
    }

    @PostMapping
    public ClaimsPageData save(@RequestBody ClaimsPageData data) {
        return service.save(data);
    }

    @GetMapping("/{id}")
    public ClaimsPageData get(@PathVariable Long id) {
        return service.get(id);
    }
}
