package com.example.tsk_insider_backend.vet;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vets")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('VOLUNTEER', 'BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
public class VetController {

    private final VetService vetService;

    @GetMapping
    public ResponseEntity<List<VetReadDTO>> getAllVets() {
        return ResponseEntity.ok(vetService.getAllVets());
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<VetReadDTO> createVet(@Valid @RequestBody VetCreateDTO vetCreateDTO) {
        Vet vet = vetService.createVet(vetCreateDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vet.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(VetReadDTO.from(vet));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Void> deleteVet(@PathVariable UUID id) {
        vetService.deleteVet(id);
        return ResponseEntity.noContent()
                .build();
    }
}
