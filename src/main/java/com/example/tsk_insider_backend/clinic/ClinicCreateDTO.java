package com.example.tsk_insider_backend.clinic;

import com.example.tsk_insider_backend.common.Address;
import com.example.tsk_insider_backend.common.PhoneNumber;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClinicCreateDTO(@NotBlank String placeName, @Valid Address address, @Email String email, @PhoneNumber String number, boolean appRegistration) {
}
