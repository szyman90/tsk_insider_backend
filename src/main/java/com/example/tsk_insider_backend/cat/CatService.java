package com.example.tsk_insider_backend.cat;

import static com.example.tsk_insider_backend.user.RoleVerificationUtil.CAT_NOT_FROM_BURROW;
import static com.example.tsk_insider_backend.user.RoleVerificationUtil.isBurrowKeeper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.vet.Vet;
import com.example.tsk_insider_backend.vet.VetRepository;

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
        existingCat.setVaccinations(updatedCat.vaccinations());
        existingCat.setNeutered(updatedCat.isNeutered());
        existingCat.setGender(updatedCat.gender());
        existingCat.setVets(updatedCat.vets());

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

        cat.getVets().add(vet);
        catRepository.save(cat);
    }

    public void assignCageToCat(UUID catId, Integer cageNumber) {
        validateCageNumber(cageNumber);

        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException(CAT_NOT_FOUND));

        cat.setCageNumber(cageNumber);
        catRepository.save(cat);
    }

    public void removeCatFromBurrow(UUID catId) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException(CAT_NOT_FOUND));

        if(cat.getCageNumber() == null) {
            throw new IllegalArgumentException("Ten kot nie jest w norze.");
        }
        cat.setCatDiseases(null);
        catRepository.save(cat);
    }

    private void validateCageNumber(Integer cageNumber) {
        List<Cat> catsInBurrow = catRepository.findByCageNumberIsNotNull();
        if (catsInBurrow.stream().anyMatch(cat -> cageNumber.equals(cat.getCageNumber()))) {
            throw new IllegalArgumentException("W klatce nr: " + cageNumber + " już jest kot.");
        }
    }

    private Cat createDTOtoEntity(CatCreateDTO catDTO) {
        Cat cat = new Cat();
        cat.setName(catDTO.name());
        cat.setAge(catDTO.age());
        cat.setTameness(catDTO.tameness());
        cat.setCageNumber(catDTO.cageNumber());
        cat.setGender(catDTO.gender());
        cat.setVaccinations(catDTO.vaccinations());
        cat.setNeutered(catDTO.isNeutered());
        cat.setWeight(catDTO.weight());
        cat.setVets(catDTO.vets());
        return cat;
    }
}