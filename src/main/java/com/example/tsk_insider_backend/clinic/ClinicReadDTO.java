package com.example.tsk_insider_backend.clinic;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.common.Address;

public record ClinicReadDTO(UUID id, String placeName, Address address, String email, String number, boolean appRegistration, List<String> vetNamesList) {
    public static ClinicReadDTO from(Clinic clinic) {
        return new ClinicReadDTO(clinic.getId(), clinic.getPlaceName(), clinic.getAddress(), clinic.getEmail(), clinic.getNumber(), clinic.isAppRegistration(),
                clinic.getVets()
                        .stream()
                        .map(vet -> vet.getName() + " " + vet.getSurname())
                        .toList());
    }
}
