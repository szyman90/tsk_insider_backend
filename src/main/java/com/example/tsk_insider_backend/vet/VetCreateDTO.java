package com.example.tsk_insider_backend.vet;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record VetCreateDTO(@NotBlank String name,@NotBlank String surname,@NotBlank UUID clinicId) {
}
