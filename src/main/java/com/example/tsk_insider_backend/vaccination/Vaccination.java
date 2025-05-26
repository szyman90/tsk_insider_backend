package com.example.tsk_insider_backend.vaccination;

import java.time.LocalDate;
import java.util.UUID;

import com.example.tsk_insider_backend.cat.Cat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vaccination {
    @Id
    private UUID id;

    @ManyToOne(optional = false)
    private Cat cat;

    private String name;

    @Enumerated(EnumType.STRING)
    private VaccinationType type;

    private LocalDate date;
}
