package com.banking.online.banking.operations.controller;

import com.banking.online.banking.operations.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/transfer")
    public ResponseEntity<Void> transferMoney(@RequestParam Long fromClientId, @RequestParam Long toClientId, @RequestParam double amount) {
        clientService.transferMoney(fromClientId, toClientId, amount);
        return ResponseEntity.ok().build();
    }
}
