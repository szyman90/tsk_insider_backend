package com.example.tsk_insider_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TskInsiderBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TskInsiderBackendApplication.class, args);
    }
    @Value("${myapp.test-message}")
    private String testMessage;

    @PostConstruct
    public void printTestMessage() {
        System.out.println(testMessage);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
