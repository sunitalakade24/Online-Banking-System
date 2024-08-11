package com.banking.online.banking.operations.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate dateOfBirth;
    private List<String> email;
    private String username;
    private String password;

    @ElementCollection
    private List<String> phoneNumbers;

    @OneToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;
}
