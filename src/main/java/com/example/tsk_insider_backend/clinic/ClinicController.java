package com.example.tsk_insider_backend.clinic;

import java.net.URI;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/clinics")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('VOLUNTEER', 'BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
public class ClinicController {
    private final ClinicService clinicService;

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<ClinicReadDTO> createClinic(@RequestBody ClinicCreateDTO clinicCreateDTO) {
        Clinic clinic = clinicService.createClinic(clinicCreateDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clinic.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(ClinicReadDTO.from(clinic));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Void> updateClinic(@PathVariable UUID id, @RequestBody @Valid ClinicUpdateDTO clinicDto) {
        clinicService.updateClinic(id, clinicDto);
        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Void> deleteClinic(@PathVariable UUID id) {
        clinicService.deleteClinic(id);
        return ResponseEntity.noContent()
                .build();
    }

    @PatchMapping("/{id}/vets")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Void> updateClinicVets(@PathVariable UUID id, @RequestBody @Valid ClinicVetUpdateDTO clinicVetUpdateDTO) throws BadRequestException {
        clinicService.updateClinicVets(id, clinicVetUpdateDTO.vetsId());
        return ResponseEntity.noContent()
                .build();
    }
    //TODO walidacja rekordów Create tu i w Cat, zamienić w rekordach na readDTO w parametrach
}
