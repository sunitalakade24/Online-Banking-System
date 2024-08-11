package com.banking.online.banking.operations.controller;

import com.banking.online.banking.operations.entity.Client;
import com.banking.online.banking.operations.repository.ClientRepository;
import com.banking.online.banking.operations.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/client")
@Tag(name="Client" , description = "This is client swagger api")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}/phones")
    public ResponseEntity<Client> updatePhones(@PathVariable Long id, @RequestBody List<String> phones) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Client client = optionalClient.get();
        client.setPhoneNumbers(phones);
        Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    @PutMapping("/{id}/emails")
    public ResponseEntity<Client> updateEmail(@PathVariable Long id, @RequestBody List<String> email) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Client client = optionalClient.get();
        client.setEmail(email);
        Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}/phones/{phoneIndex}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id, @PathVariable int phoneIndex) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Client client = optionalClient.get();
        if (phoneIndex >= 0 && phoneIndex < client.getPhoneNumbers().size()) {
            client.getPhoneNumbers().remove(phoneIndex);
            clientRepository.save(client);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}/emails/{emailIndex}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id, @PathVariable int emailIndex) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Client client = optionalClient.get();
        if (emailIndex >= 0 && emailIndex < client.getEmail().size()) {
            client.getEmail().remove(emailIndex);
            clientRepository.save(client);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
