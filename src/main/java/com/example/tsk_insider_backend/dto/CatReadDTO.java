package com.example.tsk_insider_backend.dto;

import java.util.UUID;

import com.example.tsk_insider_backend.entity.Cat;
import com.example.tsk_insider_backend.entity.Vet;
import com.example.tsk_insider_backend.enums.Gender;
import com.example.tsk_insider_backend.enums.Tameness;

public record CatReadDTO(UUID id, String name, int age, double weight, Integer cageNumber, Tameness tameness, boolean isVaccinated, boolean isNeutered,
                         Gender gender, Vet vet) {
    public static CatReadDTO from(Cat cat) {
        return new CatReadDTO(cat.getId(), cat.getName(), cat.getAge(), cat.getWeight(), cat.getCageNumber(), cat.getTameness(), cat.isVaccinated(),
                cat.isNeutered(), cat.getGender(), cat.getVet());
    }
}
