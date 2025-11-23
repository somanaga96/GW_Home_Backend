package com.example.homeinsurance.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountNumber;

    private String brandName;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String postcode;
    private String quoteRefId;
    private String address;

    public Account() {}

    // GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getQuoteRefId() { return quoteRefId; }
    public void setQuoteRefId(String quoteRefId) { this.quoteRefId = quoteRefId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
