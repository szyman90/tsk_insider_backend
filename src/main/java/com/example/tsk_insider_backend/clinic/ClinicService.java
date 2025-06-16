package com.example.tsk_insider_backend.clinic;

import java.util.List;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.vet.Vet;
import com.example.tsk_insider_backend.vet.VetRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;

    private final VetRepository vetRepository;

    public Clinic createClinic(ClinicCreateDTO clinicCreateDTO) {
        Clinic dtOtoEntity = createDTOtoEntity(clinicCreateDTO);
        return clinicRepository.save(dtOtoEntity);
    }

    public void updateClinic(UUID id, ClinicUpdateDTO clinicUpdateDTO) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        clinic.setPlaceName(clinicUpdateDTO.placeName());
        clinic.setAddress(clinicUpdateDTO.address());
        clinic.setEmail(clinicUpdateDTO.email());
        clinic.setNumber(clinicUpdateDTO.number());
        clinic.setAppRegistration(clinicUpdateDTO.appRegistration());
        clinic.setVets(clinicUpdateDTO.vetList());

        clinicRepository.save(clinic);
    }

    public void deleteClinic(UUID id) {
        if (!clinicRepository.existsById(id)) {
            throw new EntityNotFoundException("Clinic not found");
        }
        clinicRepository.deleteById(id);
    }

    @Transactional
    public void updateClinicVets(UUID clinicId, List<UUID> vetIds) throws BadRequestException {
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        List<Vet> vets = vetRepository.findAllById(vetIds);

        if (vets.size() != vetIds.size()) {
            throw new BadRequestException("One or more vets not found");
        }

        clinic.setVets(vets);

        clinicRepository.save(clinic);
    }

    private Clinic createDTOtoEntity(ClinicCreateDTO clinicCreateDTO) {
        Clinic clinic = new Clinic();
        clinic.setPlaceName(clinicCreateDTO.placeName());
        clinic.setAddress(clinicCreateDTO.address());
        clinic.setEmail(clinicCreateDTO.email());
        clinic.setNumber(clinicCreateDTO.number());
        clinic.setAppRegistration(clinicCreateDTO.appRegistration());
        return clinic;
    }


}
