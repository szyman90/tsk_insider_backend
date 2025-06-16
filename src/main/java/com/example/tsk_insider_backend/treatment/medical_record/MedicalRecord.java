package com.example.tsk_insider_backend.treatment.medical_record;

import java.time.LocalDate;
import java.util.UUID;

import com.example.tsk_insider_backend.cat.Cat;
import com.example.tsk_insider_backend.treatment.test.TestType;
import com.example.tsk_insider_backend.clinic.Clinic;
import com.example.tsk_insider_backend.vet.Vet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "medical_record")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    private Cat cat;

    @ManyToOne(optional = false)
    private Clinic clinic;

    @ManyToOne(optional = false)
    private Vet vet;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_type")
    private TestType testType;

    private LocalDate date;

    @Column(name = "result_text")
    private String resultText;

    @Column(name = "result_image")
    private byte[] resultImage;

    @Column(name = "treatment_note")
    private String treatmentNote;

}
