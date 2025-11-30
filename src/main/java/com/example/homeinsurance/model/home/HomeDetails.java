package com.example.homeinsurance.model.home;

import com.example.homeinsurance.model.quote.Quote;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "home_details")
public class HomeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One HomeDetails → One Quote
    @OneToOne
    @JoinColumn(name = "quote_id")
    @JsonBackReference("quote-home")
    private Quote quote;

    // One HomeDetails → One PropertyDetails
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_details_id")
    @JsonManagedReference("home-property")
    private PropertyDetails propertyDetails;

    // One HomeDetails → One YourNeeds
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "your_needs_id")
    @JsonManagedReference("home-needs")
    private YourNeeds yourNeeds;
}
