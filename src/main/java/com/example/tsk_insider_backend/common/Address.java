package com.example.tsk_insider_backend.common;

import jakarta.persistence.Embeddable;
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

    private String street;

    private String houseNumber;

    private String postalCode;

    private String city;

    private String country;
}
