package com.example.tsk_insider_backend.clinic;

import com.example.tsk_insider_backend.common.Address;

public record ClinicCreateDTO(String placeName, Address address, String email, String number, boolean appRegistration) {
}
