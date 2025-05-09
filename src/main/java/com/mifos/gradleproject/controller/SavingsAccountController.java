package com.mifos.gradleproject.controller;


import com.mifos.gradleproject.entity.SavingsAccount;
import com.mifos.gradleproject.entity.Transaction;
import com.mifos.gradleproject.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class SavingsAccountController {
    @Autowired
    private SavingsAccountService service;

    @PostMapping("/{clientId}")
    public ResponseEntity<SavingsAccount> createAccount(@PathVariable Long clientId) {
        return ResponseEntity.ok(service.createAccount(clientId));
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<SavingsAccount> deposit(
            @PathVariable Long accountId,
            @RequestParam Double amount) {
        return ResponseEntity.ok(service.deposit(accountId, amount));
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<SavingsAccount> withdraw(
            @PathVariable Long accountId,
            @RequestParam Double amount) {
        return ResponseEntity.ok(service.withdraw(accountId, amount));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<SavingsAccount> getAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(service.getAccount(accountId));
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountId) {
        return ResponseEntity.ok(service.getTransactions(accountId));
    }
}