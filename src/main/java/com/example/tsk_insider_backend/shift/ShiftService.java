package com.example.tsk_insider_backend.shift;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
