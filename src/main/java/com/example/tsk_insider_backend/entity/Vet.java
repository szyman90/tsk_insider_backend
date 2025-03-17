package com.example.tsk_insider_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "vet")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Vet {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String address;
}
