package com.example.tsk_insider_backend.common;

import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountryCodeValidator implements ConstraintValidator<ValidCountryCode, String> {

    private static final Set<String> COUNTRIES =
            Arrays.stream(Locale.getISOCountries())
                    .map(code -> new Locale.Builder()
                            .setRegion(code)
                            .build()
                            .getDisplayCountry(Locale.ENGLISH))
                    .collect(Collectors.toUnmodifiableSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && COUNTRIES.contains(value);
    }
}
