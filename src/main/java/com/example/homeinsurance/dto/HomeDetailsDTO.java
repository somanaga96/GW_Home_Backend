package com.example.homeinsurance.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeDetailsDTO {

    @NotNull(message = "Property details required")
    @Valid
    private PropertyDetailsDTO propertyDetails;

    @NotNull(message = "Your needs required")
    @Valid
    private YourNeedsDTO yourNeeds;
}
