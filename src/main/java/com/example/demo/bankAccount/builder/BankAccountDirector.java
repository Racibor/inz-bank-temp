package com.example.demo.bankAccount.builder;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.accountNumberGenerator.Generator;
import org.springframework.stereotype.Component;

public class BankAccountDirector {

    private final BankAccountBuilder bankAccountBuilder;

    public BankAccountDirector(BankAccountBuilder bankAccountBuilder) {
        this.bankAccountBuilder = bankAccountBuilder;
    }

    private void makeBankAccount(){
        bankAccountBuilder.buildAccountNumber();
        bankAccountBuilder.buildAccountBalance();
        bankAccountBuilder.buildAccountCurrency();
    }

    public BankAccount getBankAccount(){
        this.makeBankAccount();
        return bankAccountBuilder.getBankAccount();
    }
}
