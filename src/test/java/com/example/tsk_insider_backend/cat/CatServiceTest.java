package com.example.tsk_insider_backend.cat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {

    @Mock
    private CatRepository catRepository;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private CatService catService;

    @Test
    void shouldCreateCat_whenValidBurrowKeeperAndCageSet() {
        // given
        CatCreateDTO dto = new CatCreateDTO("Nerdy", 3, 4.5, null, Tameness.TAME, Collections.emptyList(), true, Gender.MALE, Collections.emptyList());
        final Authentication authentication = new TestingAuthenticationToken(
                null,
                null,
                "ADMIN");

        Cat savedCat = new Cat(); savedCat.setId(UUID.randomUUID());
        when(catRepository.save(any())).thenReturn(savedCat);

        // when
        Cat result = catService.createCat(dto, authentication);

        // then
        assertNotNull(result);
        verify(catRepository).save(any(Cat.class));
    }

    @Test
    void shouldThrowAccessDenied_whenBurrowKeeperWithoutCage() {
        // given
        CatCreateDTO dto = new CatCreateDTO("Eliot", 3, 4.5, null, Tameness.TAME, Collections.emptyList(), true, Gender.MALE, Collections.emptyList());
        final Authentication authentication = new TestingAuthenticationToken(
                null,
                null,
                "ROLE_BURROW_KEEPER");

        when(messageSource.getMessage(eq("burrow.keeper.cat.restriction"), any(), any())).thenReturn("No cage assigned");

        // expect
        assertThrows(AccessDeniedException.class, () -> catService.createCat(dto, authentication));
    }
}
