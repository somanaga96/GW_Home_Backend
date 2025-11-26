package com.example.homeinsurance.controller;

import com.example.homeinsurance.model.Policy;
import com.example.homeinsurance.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
@CrossOrigin(origins = "http://localhost:5173")
public class PolicyController {
    @Autowired
    private final PolicyService svc;
    public PolicyController(PolicyService svc){ this.svc = svc; }

    @GetMapping
    public List<Policy> all(){ return svc.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> one(@PathVariable Long id){
        return svc.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Policy create(@RequestBody Policy p){
        return svc.save(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> update(@PathVariable Long id, @RequestBody Policy p){
        return svc.findById(id).map(existing -> {
            // copy updated fields (you can refine)
            existing.setQuoteReferenceId(p.getQuoteReferenceId());
            existing.setBrand(p.getBrand());
            existing.setPolicyHolderName(p.getPolicyHolderName());
            existing.setPhoneNumber(p.getPhoneNumber());
            existing.setAccountAddress(p.getAccountAddress());
            existing.setPostcode(p.getPostcode());
            existing.setTownCity(p.getTownCity());
            existing.setCounty(p.getCounty());
            existing.setEmploymentStatus(p.getEmploymentStatus());
            existing.setOccupation(p.getOccupation());
            existing.setIndustry(p.getIndustry());
            existing.setTermType(p.getTermType());
            existing.setTermNumber(p.getTermNumber());
            existing.setCoverStartDate(p.getCoverStartDate());
            existing.setExpirationDate(p.getExpirationDate());
            existing.setSubmissionStartDate(p.getSubmissionStartDate());
            existing.setReasonCode(p.getReasonCode());
            existing.setChannel(p.getChannel());
            existing.setSourceCode(p.getSourceCode());
            existing.setOffering(p.getOffering());
            svc.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }
}
