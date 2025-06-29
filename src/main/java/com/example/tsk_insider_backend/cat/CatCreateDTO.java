package com.example.tsk_insider_backend.cat;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.vaccination.VaccinationCreateWithCatDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CatCreateDTO(@NotBlank String name, @Positive int age, @Positive double weight, @Min(1) @Max(12) Integer cageNumber, @NotNull Tameness tameness,
                           List<@Valid VaccinationCreateWithCatDTO> vaccinations, boolean isNeutered, @NotNull Gender gender, List<UUID> vets) {
}
