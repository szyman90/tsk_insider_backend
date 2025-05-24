package com.example.tsk_insider_backend.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shift")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    @GeneratedValue
    private UUID id;
    //TODO dokończyć
}
