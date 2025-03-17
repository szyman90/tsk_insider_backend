package com.example.tsk_insider_backend.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.entity.Cat;

public interface CatRepository extends JpaRepository<Cat, UUID> {
    List<Cat> findByCageNumberIsNotNull();
}
