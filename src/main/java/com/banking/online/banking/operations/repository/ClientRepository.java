package com.banking.online.banking.operations.repository;

import com.banking.online.banking.operations.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@EnableJpaRepositories
public interface ClientRepository  extends JpaRepository<Client,Long> {
    List<Client> findByDateOfBirthGreaterThanEqual(LocalDate dateOfBirth);
    List<Client> findByPhoneNumbersContaining(String phone);
    List<Client> findByNameContainingIgnoreCase(String name);
    List<Client> findByEmail(String email);
}
