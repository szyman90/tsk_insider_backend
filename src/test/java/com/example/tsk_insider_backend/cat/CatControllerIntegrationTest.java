package com.example.tsk_insider_backend.cat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tsk_insider_backend.auth.JwtUtil;
import com.example.tsk_insider_backend.user.UserDetailsServiceImpl;

@WebMvcTest(CatController.class)
@AutoConfigureMockMvc(addFilters = false)
class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CatService catService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Test
    void shouldReturnListOfCats() throws Exception {
        // given
        CatReadDTO cat1 = new CatReadDTO(UUID.randomUUID(), "Murphy", 3, 4.5, null,  Tameness.TAME, true, false, Gender.MALE, List.of());
        CatReadDTO cat2 = new CatReadDTO(UUID.randomUUID(), "Gall", 2, 3.0, null, Tameness.WILD, false, true, Gender.FEMALE, List.of());
        List<CatReadDTO> cats = List.of(cat1, cat2);

        when(catService.getAllCats()).thenReturn(cats);

        // when + then
        mockMvc.perform(get("/cats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Murphy"))
                .andExpect(jsonPath("$[1].name").value("Gall"));
    }
}