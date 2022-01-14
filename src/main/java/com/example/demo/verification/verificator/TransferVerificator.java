package com.example.demo.verification.verificator;

import com.example.demo.request.LoginRequest;
import com.example.demo.request.RequestOrder;
import com.example.demo.request.TransferRequest;
import com.example.demo.transfers.Transfer;
import com.example.demo.user.User;
import com.example.demo.verification.VerificationObject;
import com.example.demo.verification.VerificationService;

public class TransferVerificator extends AbstractVerificator{

    public TransferVerificator(VerificationStrategy verificationStrategy, VerificationService verificationService) {
        super(verificationStrategy, verificationService);
    }

    @Override
    public String startVerification(RequestOrder requestOrder) {
        TransferRequest transferRequest = (TransferRequest)  requestOrder;
        VerificationObject verificationObject = verificationService.registerRequest(requestOrder);
        Transfer transfer = transferRequest.getTransfer();
        String message = "you are trying to send " + transfer.getAmount() + " to " + transfer.getReceiverId() + ". validation code: " + verificationObject.getCode();
        return verificationObject.getCode();
    }

}
