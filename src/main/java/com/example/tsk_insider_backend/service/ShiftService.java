package com.example.tsk_insider_backend.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.dto.CatReadDTO;
import com.example.tsk_insider_backend.entity.Shift;
import com.example.tsk_insider_backend.record.ShiftCreateDTO;
import com.example.tsk_insider_backend.record.ShiftReadDTO;
import com.example.tsk_insider_backend.respository.ShiftRepository;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<ShiftReadDTO> getAllShifts() {
        return shiftRepository.findAll().stream()
                .map(ShiftReadDTO::from)
                .toList();
    }

    public Shift addShift(ShiftCreateDTO shiftCreateDTO, Authentication authentication) {
        return null; //TODO dokończyć
    }
}
