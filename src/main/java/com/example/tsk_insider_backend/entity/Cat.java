package com.example.tsk_insider_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.example.tsk_insider_backend.enums.Gender;
import com.example.tsk_insider_backend.enums.Tameness;

@Entity
@Table(name = "cat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cat {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int age;
    private double weight;

    @Column(name = "cage_number")
    private Integer cageNumber;

    @Enumerated(EnumType.STRING)
    private Tameness tameness;

    private boolean isVaccinated;
    private boolean isNeutered;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;
}
