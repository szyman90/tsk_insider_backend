package com.example.tsk_insider_backend.treatment.cat_disease;

import java.time.LocalDate;
import java.util.UUID;

import com.example.tsk_insider_backend.cat.Cat;
import com.example.tsk_insider_backend.common.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cat_disease")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatDisease extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @Column(nullable = false)
    private String name;

    private LocalDate diagnosisDate;

    @Enumerated(EnumType.STRING)
    private DiseaseStatus status;

    private String treatment;

    @Column(name="additional_info")
    private String additionalInfo;

    private LocalDate resolvedDate;

    private Boolean isContagious;

}
