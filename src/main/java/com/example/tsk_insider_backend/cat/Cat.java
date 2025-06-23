package com.example.tsk_insider_backend.cat;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.cat.cat_disease.CatDisease;
import com.example.tsk_insider_backend.common.Auditable;
import com.example.tsk_insider_backend.treatment.medical_record.MedicalRecord;
import com.example.tsk_insider_backend.treatment.medication.recommendation.MedicalRecommendation;
import com.example.tsk_insider_backend.vaccination.Vaccination;
import com.example.tsk_insider_backend.vet.Vet;

@Entity
@Table(name = "cat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cat extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private int age;

    private double weight;

    private String chipNumber;

    private String marking;

    @Column(name = "cage_number")
    private Integer cageNumber;

    @Enumerated(EnumType.STRING)
    private Tameness tameness;

    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    private List<Vaccination> vaccinations;

    private boolean neutered;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cat_vets",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "vet_id"))
    private List<Vet> vets;

    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    private List<CatDisease> catDiseases;

    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    private List<MedicalRecommendation> medicalRecommendations;

    @Column(name = "additional_info")
    private String additionalInfo;
}
