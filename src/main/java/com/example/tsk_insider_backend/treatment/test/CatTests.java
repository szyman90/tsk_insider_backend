package com.example.tsk_insider_backend.treatment.test;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.example.tsk_insider_backend.cat.Cat;

@Entity
@Table(name = "cat_tests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CatTests {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @Column(name = "test_type")
    private TestType testType;

    @Enumerated(EnumType.STRING)
    private TestResult result;
}
