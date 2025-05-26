package com.example.tsk_insider_backend.cat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CatRepository extends JpaRepository<Cat, UUID> {
    List<Cat> findByCageNumberIsNotNull();
}
