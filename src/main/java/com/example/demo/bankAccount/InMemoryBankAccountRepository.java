package com.example.demo.bankAccount;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBankAccountRepository implements BankAccountRepository{

    private Map<String, BankAccount> bankAccountsMap;

    public InMemoryBankAccountRepository() {
        bankAccountsMap = new ConcurrentHashMap<>();

        BankAccount bankAccount1 = new BankAccount("200040003000", new BigDecimal("100.24"), "PLN");
        bankAccountsMap.put(bankAccount1.getAccountNumber(), bankAccount1);

        BankAccount bankAccount2 = new BankAccount("345623450984", new BigDecimal("864.72"), "PLN");
        bankAccountsMap.put(bankAccount2.getAccountNumber(), bankAccount2);

        BankAccount bankAccount3 = new BankAccount("2342353453445", new BigDecimal("100.72"), "PLN");
        bankAccountsMap.put(bankAccount3.getAccountNumber(), bankAccount3);
    }

    @Override
    public BankAccount getBankAccount(String accountNUmber) {
        return bankAccountsMap.get(accountNUmber);
    }

    @Override
    public void register(BankAccount bankAccount) {
        bankAccountsMap.put(bankAccount.getAccountNumber(), bankAccount);
    }
}
