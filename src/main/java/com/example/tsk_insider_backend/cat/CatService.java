package com.example.tsk_insider_backend.cat;

import static com.example.tsk_insider_backend.user.RoleVerificationUtil.isBurrowKeeper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.tsk_insider_backend.vet.Vet;
import com.example.tsk_insider_backend.vet.VetRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    private final VetRepository vetRepository;

    private final MessageSource messageSource;

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
            throw new AccessDeniedException(messageSource.getMessage("burrow.keeper.not.allowed", new Object[]{}, LocaleContextHolder.getLocale()));
        }
        Cat dtOtoEntity = createDTOtoEntity(catDTO);
        return catRepository.save(dtOtoEntity);
    }

    public void updateCat(UUID id, CatUpdateDTO updatedCat, Authentication authentication) {
        Cat existingCat = iDontHaveNameYet(id, authentication);

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
        Cat cat = iDontHaveNameYet(id, authentication);

        catRepository.delete(cat);
    }

    public void addVet(UUID catId, UUID vetId, Authentication authentication) {
        Cat cat = iDontHaveNameYet(catId, authentication);

        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("vet.not.found", new Object[]{vetId}, LocaleContextHolder.getLocale())));

        cat.getVets().add(vet);
        catRepository.save(cat);
    }

    private Cat iDontHaveNameYet(UUID id, Authentication authentication) { //TODO names
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("cat.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));

        if ( isBurrowKeeper(authentication) && cat.getCageNumber() == null ) {
            throw new AccessDeniedException(messageSource.getMessage("burrow.keeper.not.allowed", new Object[]{}, LocaleContextHolder.getLocale())); //TODO name kodu
        }
        return cat;
    }

    public void assignCageToCat(UUID id, Integer cageNumber) {
        validateCageNumber(cageNumber);

        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("cat.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));

        cat.setCageNumber(cageNumber);
        catRepository.save(cat);
    }

    public void removeCatFromBurrow(UUID id) {
        Cat cat = catRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("cat.not.found", new Object[]{id}, LocaleContextHolder.getLocale())));

        if(cat.getCageNumber() == null) {
            throw new IllegalArgumentException(messageSource.getMessage("cat.not.found.burrow", new Object[]{cat.getName()}, LocaleContextHolder.getLocale()));
        }
        cat.setCatDiseases(null);
        catRepository.save(cat);
    }

    private void validateCageNumber(Integer cageNumber) {
        List<Cat> catsInBurrow = catRepository.findByCageNumberIsNotNull();
        if (catsInBurrow.stream().anyMatch(cat -> cageNumber.equals(cat.getCageNumber()))) {
            throw new IllegalArgumentException(messageSource.getMessage("cage.already.full", new Object[]{cageNumber}, LocaleContextHolder.getLocale()));
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