package com.example.homeinsurance.controller.home;

import com.example.homeinsurance.model.home.PropertyDetails;
import com.example.homeinsurance.service.PropertyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
@CrossOrigin(origins = "http://localhost:5173")
public class PropertyDetailsController {
    @Autowired
    private final PropertyDetailsService service;

    public PropertyDetailsController(PropertyDetailsService service) {
        this.service = service;
    }

    @PostMapping
    public PropertyDetails create(@RequestBody PropertyDetails p) {
        return service.save(p);
    }

    @PutMapping("/{id}")
    public PropertyDetails update(@PathVariable Long id, @RequestBody PropertyDetails p) {
        p.setId(id);
        return service.save(p);
    }

    @GetMapping("/{id}")
    public PropertyDetails get(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<PropertyDetails> getAll() {
        return service.findAll();
    }
}
