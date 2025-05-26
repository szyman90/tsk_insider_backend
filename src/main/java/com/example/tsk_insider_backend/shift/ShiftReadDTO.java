package com.example.tsk_insider_backend.shift;

public record ShiftReadDTO() {

    public static ShiftReadDTO from(Shift shift) {
        return new ShiftReadDTO();
        //TODO dokończyć
    }
}
