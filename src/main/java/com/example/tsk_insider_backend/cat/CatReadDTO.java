package com.example.tsk_insider_backend.cat;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.vet.Vet;

public record CatReadDTO(UUID id, String name, int age, double weight, Integer cageNumber, Tameness tameness, boolean isVaccinated, boolean isNeutered,
                         Gender gender, List<Vet> vet) {
    public static CatReadDTO from(Cat cat) {
        return new CatReadDTO(cat.getId(), cat.getName(), cat.getAge(), cat.getWeight(), cat.getCageNumber(), cat.getTameness(), false,
                cat.isNeutered(), cat.getGender(), cat.getVet());
    }
}
