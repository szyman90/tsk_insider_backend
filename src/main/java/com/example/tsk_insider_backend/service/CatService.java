package com.example.tsk_insider_backend.service;

import static com.example.tsk_insider_backend.util.RoleVerificationUtil.CAT_NOT_FROM_BURROW;
import static com.example.tsk_insider_backend.util.RoleVerificationUtil.isBurrowKeeper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.dto.CatCreateDTO;
import com.example.tsk_insider_backend.dto.CatReadDTO;
import com.example.tsk_insider_backend.dto.CatUpdateDTO;
import com.example.tsk_insider_backend.entity.Cat;
import com.example.tsk_insider_backend.entity.Vet;
import com.example.tsk_insider_backend.respository.CatRepository;
import com.example.tsk_insider_backend.respository.VetRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CatService {

    public static final String CAT_NOT_FOUND = "Cat not found";

    private final CatRepository catRepository;

    private final VetRepository vetRepository;

    public CatService(CatRepository catRepository, VetRepository vetRepository) {
        this.catRepository = catRepository;
        this.vetRepository = vetRepository;
    }

    public List<CatReadDTO> getAllCats() {
        return catRepository.findAll().stream()
                .map(CatReadDTO::from)
                .toList();
    }

    public Optional<Cat> getCatById(UUID id) {
        return catRepository.findById(id);
    }

    public List<Cat> getCatsInBurrow() {
        return catRepository.findByCageNumberIsNotNull();
    }

    public Cat createCat(CatCreateDTO catDTO, Authentication authentication) {
        if (isBurrowKeeper(authentication) && catDTO.cageNumber() == null) {
            throw new AccessDeniedException(CAT_NOT_FROM_BURROW);
        }
        Cat dtOtoEntity = createDTOtoEntity(catDTO);
        return catRepository.save(dtOtoEntity);
    }

    public void updateCat(UUID id, CatUpdateDTO updatedCat, Authentication authentication) {
        Cat existingCat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CAT_NOT_FOUND));

        if (isBurrowKeeper(authentication) && existingCat.getCageNumber() == null) {
            throw new AccessDeniedException(CAT_NOT_FROM_BURROW);
        }

        existingCat.setName(updatedCat.name());
        existingCat.setAge(updatedCat.age());
        existingCat.setWeight(updatedCat.weight());
        existingCat.setCageNumber(updatedCat.cageNumber());
        existingCat.setTameness(updatedCat.tameness());
        existingCat.setVaccinated(updatedCat.isVaccinated());
        existingCat.setNeutered(updatedCat.isNeutered());
        existingCat.setGender(updatedCat.gender());
        existingCat.setVet(updatedCat.vet());

        catRepository.save(existingCat);
    }

    public void deleteCat(UUID id, Authentication authentication) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CAT_NOT_FOUND));

        if (isBurrowKeeper(authentication) && cat.getCageNumber() == null) {
            throw new AccessDeniedException(CAT_NOT_FROM_BURROW);
        }

        catRepository.deleteById(id);
    }

    public void addVet(UUID catId, UUID vetId, Authentication authentication) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException(CAT_NOT_FOUND));

        if ( isBurrowKeeper(authentication) && cat.getCageNumber() == null ) {
            throw new AccessDeniedException(CAT_NOT_FROM_BURROW);
        }

        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new EntityNotFoundException("Vet not found"));

        cat.getVet().add(vet);
        catRepository.save(cat);
    }

    private Cat createDTOtoEntity(CatCreateDTO catDTO) {
        Cat cat = new Cat();
        cat.setName(catDTO.name());
        cat.setAge(catDTO.age());
        cat.setTameness(catDTO.tameness());
        cat.setCageNumber(catDTO.cageNumber());
        cat.setGender(catDTO.gender());
        cat.setVaccinated(catDTO.isVaccinated());
        cat.setNeutered(catDTO.isNeutered());
        cat.setWeight(catDTO.weight());
        cat.setVet(catDTO.vet());
        return cat;
    }
}