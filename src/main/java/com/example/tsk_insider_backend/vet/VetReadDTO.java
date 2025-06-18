package com.example.tsk_insider_backend.vet;

import java.util.UUID;

public record VetReadDTO(UUID id, String name, String surname, String clinicName) {
    public static VetReadDTO from(Vet vet) {
        return new VetReadDTO(vet.getId(), vet.getName(), vet.getSurname(), vet.getClinic().getPlaceName());
    }
}
