package com.example.tsk_insider_backend.clinic;

import java.util.List;

import com.example.tsk_insider_backend.common.Address;
import com.example.tsk_insider_backend.vet.Vet;

public record ClinicUpdateDTO(String placeName, Address address, String email, String number, boolean appRegistration, List<Vet> vetList) {
}
