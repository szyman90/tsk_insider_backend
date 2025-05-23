package com.example.tsk_insider_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.dto.CatCreateDTO;
import com.example.tsk_insider_backend.dto.CatUpdateDTO;
import com.example.tsk_insider_backend.entity.Cat;
import com.example.tsk_insider_backend.entity.Vet;
import com.example.tsk_insider_backend.respository.CatRepository;
import com.example.tsk_insider_backend.respository.VetRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CatService {
    private final CatRepository catRepository;

    private final VetRepository vetRepository;

    public CatService(CatRepository catRepository, VetRepository vetRepository) {
        this.catRepository = catRepository;
        this.vetRepository = vetRepository;
    }

    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    public Optional<Cat> getCatById(UUID id) {
        return catRepository.findById(id);
    }

    public List<Cat> getCatsInBurrow() {
        return catRepository.findByCageNumberIsNotNull();
    }

    public Cat createCat(CatCreateDTO catDTO, Authentication authentication) {
        if (isBurrowKeeper(authentication) && catDTO.cageNumber() == null) {
            throw new AccessDeniedException("BURROW_KEEPER może dodawać tylko koty do nory!");
        }
        Cat dtOtoEntity = createDTOtoEntity(catDTO);
        return catRepository.save(dtOtoEntity);
    }

    public void updateCat(UUID id, CatUpdateDTO updatedCat, Authentication authentication) {
        Cat existingCat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));

        if (isBurrowKeeper(authentication) && existingCat.getCageNumber() == null) {
            throw new AccessDeniedException("BURROW_KEEPER może edytować tylko koty w norze!");
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
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));

        if (isBurrowKeeper(authentication) && cat.getCageNumber() == null) { //TODO przed resztą zablokuj
            throw new AccessDeniedException("BURROW_KEEPER może usuwać tylko koty w norze!");
        }

        catRepository.deleteById(id);
    }

    public void addVet(UUID catId, UUID vetId, Authentication authentication) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));

        if ( isBurrowKeeper(authentication) && cat.getCageNumber() == null ) { //TODO przed resztą zablokuj
            throw new AccessDeniedException("BURROW_KEEPER może usuwać tylko koty w norze!");
        }

        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new EntityNotFoundException("Vet not found"));

        cat.getVet().add(vet);
        catRepository.save(cat);
    }

    private boolean isBurrowKeeper(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority()
                        .equals("ROLE_BURROW_KEEPER"));
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