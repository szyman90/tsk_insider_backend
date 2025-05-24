package com.example.tsk_insider_backend.util;

import org.springframework.security.core.Authentication;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleVerificationUtil {

    public static final String CAT_NOT_FROM_BURROW = "Zarządca Nory może dodawać tylko koty do kociarni.";

    public static boolean isBurrowKeeper(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority()
                        .equals("ROLE_BURROW_KEEPER"));
    }
}
