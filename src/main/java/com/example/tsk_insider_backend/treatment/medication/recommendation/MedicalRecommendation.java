package com.example.tsk_insider_backend.treatment.medication.recommendation;

import java.time.LocalDate;

import com.example.tsk_insider_backend.cat.Cat;
import com.example.tsk_insider_backend.treatment.medication.Medication;
import com.example.tsk_insider_backend.treatment.medication.MedicationFrequency;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medical_recommendation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Cat cat;

    @ManyToOne(optional = false)
    private Medication medication;

    private Double dosage;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private MedicationFrequency frequency;

    private String notes;

    private boolean active;
}
