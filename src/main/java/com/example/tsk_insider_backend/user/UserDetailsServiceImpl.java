package com.example.tsk_insider_backend.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tsk_insider_backend.shift.ShiftCreateDTO;
import com.example.tsk_insider_backend.shift.Shift;
import com.example.tsk_insider_backend.shift.ShiftReadDTO;
import com.example.tsk_insider_backend.shift.ShiftService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @RestController
    @RequestMapping("/shift")
    @PreAuthorize("hasAnyRole('VOLUNTEER', 'BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
    public static class ShiftController {

        ShiftService shiftService;

        @PreAuthorize("hasAnyRole('BURROW_KEEPER', 'MANAGEMENT', 'ADMIN')")
        @PostMapping
        public ResponseEntity<ShiftReadDTO> addNewShift(@RequestBody ShiftCreateDTO shiftCreateDTO, Authentication authentication) {
            Shift shift = shiftService.addShift(shiftCreateDTO, authentication);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(shift.getId())
                    .toUri();

            return ResponseEntity.created(location)
                    .body(ShiftReadDTO.from(shift));
        }

        @GetMapping
        public ResponseEntity<List<ShiftReadDTO>> getAllShift() {
            return ResponseEntity.ok(shiftService.getAllShifts());
        }
    }
}
