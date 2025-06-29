package com.example.tsk_insider_backend.common;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotBlank
    private String street;

    @Positive
    private int houseNumber;

    private String postalCode;

    @Pattern(regexp = "^\\p{L}[\\p{L} \\-']{1,49}$", message = "Incorrect name of the town.")
    private String city;

    @ValidCountryCode
    private String country;
}
