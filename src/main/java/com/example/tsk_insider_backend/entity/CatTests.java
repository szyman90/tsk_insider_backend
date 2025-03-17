package com.example.tsk_insider_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.example.tsk_insider_backend.enums.TestResult;

@Entity
@Table(name = "cat_tests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CatTests {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    private String testType;

    @Enumerated(EnumType.STRING)
    private TestResult result;
}
