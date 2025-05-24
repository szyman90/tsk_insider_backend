package com.example.tsk_insider_backend.respository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tsk_insider_backend.entity.Shift;

public interface ShiftRepository extends JpaRepository<Shift, UUID> {
}
