package com.example.tsk_insider_backend.cat;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cats")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('VOLUNTEER', 'BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
public class CatController {
    private final CatService catService;

    @GetMapping
    public ResponseEntity<List<CatReadDTO>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatReadDTO> getCatById(@PathVariable UUID id) {
        return catService.getCatById(id)
                .map(CatReadDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PostMapping
    public ResponseEntity<CatReadDTO> createCat(@Valid @RequestBody CatCreateDTO catCreateDTO, Authentication authentication) {
        Cat cat = catService.createCat(catCreateDTO, authentication);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cat.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(CatReadDTO.from(cat));
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCat(@PathVariable UUID id, @RequestBody @Valid CatUpdateDTO cat, Authentication authentication) {
        catService.updateCat(id, cat, authentication);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable UUID id, Authentication authentication) {
        catService.deleteCat(id, authentication);
        return ResponseEntity.noContent()
                .build();
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PatchMapping("/{id}/burrow/add")
    public ResponseEntity<Void> addToBurrow(@PathVariable UUID id, @RequestBody @Valid Integer cageNumber) {
        catService.assignCageToCat(id, cageNumber);
        return ResponseEntity.noContent()
                .build();
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PatchMapping("/{id}/burrow/remove")
    public ResponseEntity<Void> removeFromBurrow(@PathVariable UUID id) {
        catService.removeCatFromBurrow(id);
        return ResponseEntity.noContent()
                .build();
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @GetMapping("/burrow")
    public ResponseEntity<List<CatReadDTO>> getCatsInBurrow() {
        return ResponseEntity.ok(catService.getCatsInBurrow()
                .stream()
                .map(CatReadDTO::from)
                .toList());
    }

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PatchMapping("/{catId}/vet")
    public ResponseEntity<Void> addVet(@PathVariable UUID catId, @RequestBody UUID vetId, Authentication auth) {
        catService.addVet(catId, vetId, auth);
        return ResponseEntity.noContent().build();
    }
}
