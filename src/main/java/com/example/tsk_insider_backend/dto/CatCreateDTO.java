package com.example.tsk_insider_backend.dto;

import java.util.List;

import com.example.tsk_insider_backend.entity.Vet;
import com.example.tsk_insider_backend.enums.Gender;
import com.example.tsk_insider_backend.enums.Tameness;

public record CatCreateDTO(String name, int age, double weight, Integer cageNumber, Tameness tameness, boolean isVaccinated, boolean isNeutered,
                           Gender gender, List<Vet> vet){
}
