package com.example.tsk_insider_backend.cat;

import java.util.List;

import com.example.tsk_insider_backend.vaccination.Vaccination;
import com.example.tsk_insider_backend.vet.Vet;

public record CatCreateDTO(String name, int age, double weight, Integer cageNumber, Tameness tameness, List<Vaccination> vaccinations, boolean isNeutered,
                           Gender gender, List<Vet> vets){
}
