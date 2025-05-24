package com.example.tsk_insider_backend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tsk_insider_backend.entity.Shift;
import com.example.tsk_insider_backend.record.ShiftCreateDTO;
import com.example.tsk_insider_backend.record.ShiftReadDTO;
import com.example.tsk_insider_backend.service.ShiftService;

@RestController
@RequestMapping("/shift")
@PreAuthorize("hasAnyRole('VOLUNTEER', 'BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
public class ShiftController {

    ShiftService shiftService;

    @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    @PostMapping
    public ResponseEntity<ShiftReadDTO> addNewShift(@RequestBody ShiftCreateDTO shiftCreateDTO, Authentication authentication) {
        Shift shift = shiftService.addShift(shiftCreateDTO, authentication);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shift.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(ShiftReadDTO.from(shift));
    }

    @GetMapping
    public ResponseEntity<List<ShiftReadDTO>> getAllShift() {
        return ResponseEntity.ok(shiftService.getAllShifts());
    }
}
