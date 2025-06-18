package com.example.tsk_insider_backend.vet;

import java.util.UUID;


public record VetCreateDTO(String name, String surname, UUID clinicId) {
}
