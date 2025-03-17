package com.example.tsk_insider_backend.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import com.example.tsk_insider_backend.entity.Vet;

public interface VetRepository extends JpaRepository<Vet, UUID> {
}
