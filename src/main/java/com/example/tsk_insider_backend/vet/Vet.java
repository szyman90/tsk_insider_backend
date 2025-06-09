package com.example.tsk_insider_backend.vet;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.example.tsk_insider_backend.common.Auditable;

@Entity
@Table(name = "vet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Vet extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String surname;

    @ManyToOne(optional = false)
    private Clinic clinic;
}
