package com.example.tsk_insider_backend.entity;

import java.util.UUID;

import com.example.tsk_insider_backend.enums.DosageUnit;
import com.example.tsk_insider_backend.enums.MedicationForm;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private MedicationForm medicationForm;

    @Enumerated(EnumType.STRING)
    private DosageUnit dosageUnit;


}
