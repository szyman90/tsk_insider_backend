package com.example.tsk_insider_backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tsk_insider_backend.entity.Cat;
import com.example.tsk_insider_backend.service.CatService;

@RestController
@RequestMapping("/cats")
@PreAuthorize("hasAnyRole('VOLUNTEER', 'BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCatById(@PathVariable UUID id) {
        return catService.getCatById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/burrow")
    public ResponseEntity<List<Cat>> getCatsInBurrow() {
        return ResponseEntity.ok(catService.getCatsInBurrow());
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat, Authentication authentication) {
        return ResponseEntity.ok(catService.addCat(cat, authentication));
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable UUID id, @RequestBody Cat cat, Authentication authentication) {
        return ResponseEntity.ok(catService.updateCat(id, cat, authentication));
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable UUID id, Authentication authentication) {
        catService.deleteCat(id, authentication);
        return ResponseEntity.noContent().build();
    }
}
