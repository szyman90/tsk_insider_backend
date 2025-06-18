package com.example.tsk_insider_backend.vet;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tsk_insider_backend.clinic.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, UUID> {
}