package com.example.demo.bankAccount;

public interface BankAccountService {

    BankAccount getBankAccount(String accountNumber);
    void registerBankAccount(BankAccount bankAccount);

}
