package com.banking.online.banking.operations.service;

import com.banking.online.banking.operations.entity.BankAccount;
import com.banking.online.banking.operations.entity.Client;
import com.banking.online.banking.operations.repository.BankRepository;
import com.banking.online.banking.operations.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankRepository bankRepository;

    public Client createClient(Client client) {
        if (client.getBankAccount().getBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        return clientRepository.save(client);
    }

    public void transferMoney(Long fromClientId, Long toClientId, double amount) {
        Client fromClient = clientRepository.findById(fromClientId).orElseThrow();
        Client toClient = clientRepository.findById(toClientId).orElseThrow();

        if (fromClient.getBankAccount().getBalance() - amount < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        fromClient.getBankAccount().setBalance(fromClient.getBankAccount().getBalance() - amount);
        toClient.getBankAccount().setBalance(toClient.getBankAccount().getBalance() + amount);

        clientRepository.save(fromClient);
        clientRepository.save(toClient);
    }

    public List<Client> searchClients(String name, String phone, String email, LocalDate dateOfBirth) {
        if (dateOfBirth != null) {
            return clientRepository.findByDateOfBirthGreaterThanEqual(dateOfBirth);
        } else if (phone != null) {
            return clientRepository.findByPhoneNumbersContaining(phone);
        } else if (email != null) {
            return clientRepository.findByEmail(email);
        } else if (name != null) {
            return clientRepository.findByNameContainingIgnoreCase(name);
        } else {
            return Collections.emptyList();
        }
    }

    @Scheduled(fixedRate = 60000)
    public void applyInterest() {
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            BankAccount account = client.getBankAccount();
            double interest = account.getInitialBalance() * 0.05;
            double newBalance = account.getBalance() + interest;
            if (newBalance <= account.getInitialBalance() * 2.07) {
                account.setBalance(newBalance);
                bankRepository.save(account);
            }
        }
    }
}
