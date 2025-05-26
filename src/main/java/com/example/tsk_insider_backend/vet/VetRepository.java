package com.example.tsk_insider_backend.vet;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VetRepository extends JpaRepository<Vet, UUID> {
}
