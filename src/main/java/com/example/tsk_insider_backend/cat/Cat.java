package com.example.tsk_insider_backend.cat;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.treatment.medication.recommendation.MedicalRecommendation;
import com.example.tsk_insider_backend.vaccination.Vaccination;
import com.example.tsk_insider_backend.vet.Vet;

@Entity
@Table(name = "cat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cat {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private double weight;

    @Column(name = "cage_number")
    private Integer cageNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "tameness")
    private Tameness tameness;

    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    private List<Vaccination> vaccinations;

    @Column(name = "is_neutered")
    private boolean isNeutered;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cat_vets",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "vet_id"))
    private List<Vet> vet;

    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    private List<MedicalRecommendation> medicalRecommendations;
}
