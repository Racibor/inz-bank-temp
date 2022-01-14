package com.example.demo.request;

import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountService;
import com.example.demo.card.CardService;
import com.example.demo.transfers.Transfer;
import com.example.demo.user.User;
import com.example.demo.transfers.TransferService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class RequestFactory {
    public RequestOrder createLoginRequest(HttpSession httpSession, User user) {
        return new LoginRequest(httpSession, user);
    }

    public RequestOrder createTransferRequest(Transfer transfer, TransferService transferService) {
        return new TransferRequest(transfer, transferService);
    }

    public RequestOrder createCreateCardRequest(User user, CardService cardService) {
        return new CreateCardRequest(cardService, user);
    }

    public RequestOrder createBankAccountRequest(BankAccount bankAccount, User user, BankAccountService bankAccountService){
        return new CreateBankAccountRequest(bankAccount, user, bankAccountService);
    }
}
