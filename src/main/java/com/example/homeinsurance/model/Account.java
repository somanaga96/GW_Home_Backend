package com.example.homeinsurance.model;

import com.example.homeinsurance.model.quote.Quote;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brandName;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String postcode;
    private String email;

    // ⭐ One Account → Many Submissions
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("account-submissions")
    private List<Submission> submissions = new ArrayList<>();

    // ⭐ One Account → Many Quotes
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("account-quotes")
    private List<Quote> quotes = new ArrayList<>();

    // ⭐ One Account → Many Policies
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
//    @JsonManagedReference("account-policies")
    private List<Policy> policies = new ArrayList<>();
}

