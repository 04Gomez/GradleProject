package com.mifos.gradleproject.service;

import com.mifos.gradleproject.entity.Client;
import com.mifos.gradleproject.entity.SavingsAccount;
import com.mifos.gradleproject.entity.Transaction;
import com.mifos.gradleproject.repository.ClientRepository;
import com.mifos.gradleproject.repository.SavingsAccountRepository;
import com.mifos.gradleproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SavingsAccountService {
    @Autowired
    private SavingsAccountRepository accountRepo;
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private TransactionRepository transactionRepo;

    public SavingsAccount createAccount(Long clientId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        SavingsAccount account = new SavingsAccount();
        account.setClient(client);
        return accountRepo.save(account);
    }

    public SavingsAccount deposit(Long accountId, Double amount) {
        SavingsAccount account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setDate(LocalDateTime.now());
        tx.setAmount(amount);
        tx.setType("DEPOSIT");
        transactionRepo.save(tx);
        return accountRepo.save(account);
    }

    public SavingsAccount withdraw(Long accountId, Double amount) {
        SavingsAccount account = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) throw new RuntimeException("Insufficient funds");
        account.setBalance(account.getBalance() - amount);
        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setDate(LocalDateTime.now());
        tx.setAmount(amount);
        tx.setType("WITHDRAWAL");
        transactionRepo.save(tx);
        return accountRepo.save(account);
    }

    public SavingsAccount getAccount(Long accountId) {
        return accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Transaction> getTransactions(Long accountId) {
        return getAccount(accountId).getTransactions();
    }
}
