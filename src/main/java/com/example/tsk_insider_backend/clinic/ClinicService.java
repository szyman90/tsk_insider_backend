package com.example.tsk_insider_backend.clinic;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.vet.VetReadDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClinicService {

    private final ClinicRepository clinicRepository;

    private final MessageSource messageSource;

    public Clinic createClinic(ClinicCreateDTO clinicCreateDTO) {
        Clinic dtOtoEntity = createDTOtoEntity(clinicCreateDTO);
        return clinicRepository.save(dtOtoEntity);
    }

    public void updateClinic(UUID id, ClinicUpdateDTO clinicUpdateDTO) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException(messageSource.getMessage("clinic.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));

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
            throw new EntityNotFoundException(messageSource.getMessage("clinic.not.found", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
        clinicRepository.deleteById(id);
    }

    public List<VetReadDTO> getVets(UUID id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("clinic.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));
        return clinic.getVets().stream().map(VetReadDTO::from)
                .toList();
    }

    private Clinic createDTOtoEntity(ClinicCreateDTO clinicCreateDTO) {
        Clinic clinic = new Clinic();
        clinic.setPlaceName(clinicCreateDTO.placeName());
        clinic.setAddress(clinicCreateDTO.address());
        clinic.setEmail(clinicCreateDTO.email());
        clinic.setNumber(clinicCreateDTO.number());
        clinic.setAppRegistration(clinicCreateDTO.appRegistration());
        clinic.setVets(Collections.emptyList());
        return clinic;
    }
}
