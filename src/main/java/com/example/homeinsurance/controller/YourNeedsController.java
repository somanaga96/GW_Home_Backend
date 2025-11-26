package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.YourNeeds;
import com.example.homeinsurance.service.YourNeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/your-needs")
@CrossOrigin(origins = "http://localhost:5173")
public class YourNeedsController {
    @Autowired

    private final YourNeedsService service;

    public YourNeedsController(YourNeedsService service) {
        this.service = service;
    }

    @PostMapping
    public YourNeeds save(@RequestBody YourNeeds data) {
        return service.save(data);
    }

    @GetMapping("/{id}")
    public YourNeeds get(@PathVariable Long id) {
        return service.get(id);
    }
}
