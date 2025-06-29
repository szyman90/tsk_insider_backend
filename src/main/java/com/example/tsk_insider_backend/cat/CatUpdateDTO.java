package com.example.tsk_insider_backend.cat;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CatUpdateDTO(@NotBlank String name, @Positive Integer age, @Positive Double weight, Integer cageNumber, @NotNull Tameness tameness,
                           @NotNull List<UUID> vaccinations, @NotNull Boolean isNeutered, @NotNull Gender gender, List<UUID> vets) {
}
