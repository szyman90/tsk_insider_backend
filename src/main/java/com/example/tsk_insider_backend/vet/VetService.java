package com.example.tsk_insider_backend.vet;

import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.clinic.Clinic;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service

@AllArgsConstructor
public class VetService {

    private final VetRepository vetRepository;

    private final ClinicRepository clinicRepository;

    private final MessageSource messageSource;

    public Vet createVet(VetCreateDTO vetCreateDTO) {
        UUID clinicId = vetCreateDTO.clinicId();
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("clinic.not.found", new Object[]{clinicId}, LocaleContextHolder.getLocale())));

        Vet dtOtoEntity = createDTOtoEntity(vetCreateDTO, clinic);
        return vetRepository.save(dtOtoEntity);
    }

    public void deleteVet(UUID id) {
        if (!vetRepository.existsById(id)) {
            throw new EntityNotFoundException(messageSource.getMessage("vet.not.found", new Object[]{id}, LocaleContextHolder.getLocale()));
        }
        vetRepository.deleteById(id);
    }

    private Vet createDTOtoEntity(VetCreateDTO vetCreateDTO, Clinic clinic) {
        Vet vet = new Vet();
        vet.setName(vetCreateDTO.name());
        vet.setSurname(vetCreateDTO.surname());

        vet.setClinic(clinic);
        return vet;
    }
}
