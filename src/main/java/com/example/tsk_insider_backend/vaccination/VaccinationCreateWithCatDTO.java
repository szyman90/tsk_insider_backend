package com.example.tsk_insider_backend.vaccination;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record VaccinationCreateWithCatDTO(@NotNull VaccinationType type, @PastOrPresent LocalDate date) {
}