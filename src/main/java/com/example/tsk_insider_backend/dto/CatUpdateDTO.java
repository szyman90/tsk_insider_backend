package com.example.tsk_insider_backend.dto;

import com.example.tsk_insider_backend.entity.Vet;
import com.example.tsk_insider_backend.enums.Gender;
import com.example.tsk_insider_backend.enums.Tameness;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CatUpdateDTO(@NotBlank String name, @Positive Integer age, @Positive Double weight, Integer cageNumber, @NotNull Tameness tameness,
                           @NotNull Boolean isVaccinated, @NotNull Boolean isNeutered, @NotNull Gender gender, Vet vet) {
}
