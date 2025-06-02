package com.example.tsk_insider_backend.vet;

import java.util.List;
import java.util.UUID;

import com.example.tsk_insider_backend.common.Address;
import com.example.tsk_insider_backend.common.PhoneNumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "clinic")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "place_name")
    private String placeName;

    @Embedded
    private Address address;

    @Email
    private String email;

    @PhoneNumber
    private String number;

    private boolean appRegistration;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Vet> vets;

}
