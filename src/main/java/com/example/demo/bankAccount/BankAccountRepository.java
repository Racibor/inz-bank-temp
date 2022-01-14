package com.example.demo.bankAccount;


public interface BankAccountRepository {

    BankAccount getBankAccount(String accountNUmber);
    void register(BankAccount bankAccount);

}
