package com.example.tsk_insider_backend.clinic;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tsk_insider_backend.vet.VetReadDTO;

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

    @GetMapping("/{id}/vets")
    public ResponseEntity<List<VetReadDTO>> getVets(@PathVariable UUID id) {
        return ResponseEntity.ok(clinicService.getVets(id));
    }

}
