package com.example.tsk_insider_backend.record;

import com.example.tsk_insider_backend.entity.Shift;

public record ShiftReadDTO() {

    public static ShiftReadDTO from(Shift shift) {
        return new ShiftReadDTO();
        //TODO dokończyć
    }
}
