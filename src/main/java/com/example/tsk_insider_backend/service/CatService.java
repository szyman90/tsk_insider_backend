package com.example.tsk_insider_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.entity.Cat;
import com.example.tsk_insider_backend.respository.CatRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
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

    public Cat addCat(Cat cat, Authentication authentication) {
        if (isBurrowKeeper(authentication) && cat.getCageNumber() == null) {
            throw new AccessDeniedException("BURROW_KEEPER może dodawać tylko koty do nory!");
        }
        return catRepository.save(cat);
    }

    public Cat updateCat(UUID id, Cat updatedCat, Authentication authentication) {
        Cat existingCat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));

        if (isBurrowKeeper(authentication) && existingCat.getCageNumber() == null) {
            throw new AccessDeniedException("BURROW_KEEPER może edytować tylko koty w norze!");
        }

        existingCat.setName(updatedCat.getName());
        existingCat.setAge(updatedCat.getAge());
        existingCat.setWeight(updatedCat.getWeight());
        existingCat.setCageNumber(updatedCat.getCageNumber());
        existingCat.setTameness(updatedCat.getTameness());
        existingCat.setVaccinated(updatedCat.isVaccinated());
        existingCat.setNeutered(updatedCat.isNeutered());
        existingCat.setGender(updatedCat.getGender());
        existingCat.setVet(updatedCat.getVet());

        return catRepository.save(existingCat);
    }

    public void deleteCat(UUID id, Authentication authentication) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));

        if (isBurrowKeeper(authentication) && cat.getCageNumber() == null) {
            throw new AccessDeniedException("BURROW_KEEPER może usuwać tylko koty w norze!");
        }

        catRepository.deleteById(id);
    }

    private boolean isBurrowKeeper(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_BURROW_KEEPER"));
    }
}