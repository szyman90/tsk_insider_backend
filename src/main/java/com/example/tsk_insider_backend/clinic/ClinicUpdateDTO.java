package com.example.tsk_insider_backend.clinic;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.common.Address;

public record ClinicUpdateDTO(String placeName, Address address, String email, String number, boolean appRegistration, List<UUID> vetList) {
}
