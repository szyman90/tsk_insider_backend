package com.example.tsk_insider_backend.shift;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, UUID> {
}
