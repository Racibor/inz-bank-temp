package com.example.demo.verification.verificator;

import com.example.demo.verification.verificator.EmailVerificationStrategy;
import com.example.demo.verification.verificator.VerificationStrategy;
import com.example.demo.verification.verificator.VerificatorAbstractFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class VerificationStrategyFactory {

    public VerificationStrategy getDesiredStrategy(VerificationType type) {
        switch(type) {
            case EMAIL:
                return new EmailVerificationStrategy();
            default:
                return null;
        }
    }
}
