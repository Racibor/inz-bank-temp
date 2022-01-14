package com.example.demo.controllers.rest;

import com.example.demo.request.RequestFactory;
import com.example.demo.request.RequestOrder;
import com.example.demo.request.TransferRequest;
import com.example.demo.transfers.Transfer;
import com.example.demo.transfers.TransferService;
import com.example.demo.verification.VerificationService;
import com.example.demo.verification.verificator.AbstractVerificator;
import com.example.demo.verification.verificator.VerificationType;
import com.example.demo.verification.verificator.VerificatorAbstractFactory;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class TransferController {

    private TransferService transferService;
    private RequestFactory requestFactory;

    private VerificatorAbstractFactory verificatorAbstractFactory;

    public TransferController(TransferService transferService, VerificatorAbstractFactory verificatorAbstractFactory) {
        this.transferService = transferService;
        this.requestFactory = new RequestFactory();
        this.verificatorAbstractFactory = verificatorAbstractFactory;
    }

    @PostMapping("/transfer")
    public Map<String, String>transfer(@RequestBody JsonNode payload, SessionStatus sessionStatus)
    {
        sessionStatus.setComplete(); //Deleting account attribute from the session

        Map<String, String> response = new HashMap<>();
        String accountNumber = payload.get("accountNumber").toString().replace("\"","");
        String reciever = payload.get("reciever").toString().replace("\"","");
        BigDecimal amount = new BigDecimal(payload.get("amount").asDouble());
        System.out.println(accountNumber+" "+reciever+" "+amount);


        Transfer transfer = new Transfer(transferService.getNextId(), accountNumber, reciever, amount);

        RequestOrder transferRequest = requestFactory.createTransferRequest(transfer, transferService);
        AbstractVerificator transferVerificator = verificatorAbstractFactory.getTransferVerificator(VerificationType.EMAIL);

        String id = transferVerificator.startVerification(transferRequest);
        response.put("requestId", id);
        return response;
    }
}
