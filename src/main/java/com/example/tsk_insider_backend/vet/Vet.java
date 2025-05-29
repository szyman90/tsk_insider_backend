package com.example.tsk_insider_backend.vet;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.treatment.medical_record.MedicalRecord;

@Entity
@Table(name = "vet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String surname;

    @ManyToOne(optional = false)
    private Clinic clinic;

    @OneToMany(mappedBy = "vet", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;
}
