package com.example.demo.controllers.view;


import com.example.demo.bankAccount.BankAccount;
import com.example.demo.bankAccount.BankAccountRepository;
import com.example.demo.currency.CurrencyService;
import com.example.demo.currency.StringWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class CurrencyConversionController {

    private CurrencyService currencyService;
    private BankAccountRepository bankAccountRepository;

    public CurrencyConversionController(CurrencyService currencyService, BankAccountRepository bankAccountRepository) {
        this.currencyService = currencyService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/currency/currency-conversion")
    public String currencyConversion(Model model, @RequestParam("accountNumber") String accountNumber){
        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);

        model.addAttribute("currentCurrency", new StringWrapper(bankAccount.getCurrency()));
        model.addAttribute("accountNumber", accountNumber);

        List<String> currencies = currencyService.getCurrenciesWithoutCode(bankAccount.getCurrency());
        model.addAttribute("currencies", currencies);

        return "/client/currency-conversion";
    }

    @PutMapping("/currency/processing")
    public String processingCurrencyConversion(@ModelAttribute("currentCurrency") StringWrapper newCurrencyCode,
                                               @RequestParam("accountNumber") String accountNumber){

        BankAccount bankAccount = bankAccountRepository.getBankAccount(accountNumber);
        currencyService.convertAccountToNewCurrency(bankAccount, newCurrencyCode.getValue());

        //TODO tu będzie kiedyś update konta bankowego w bazie

        return "redirect:/client/home";
    }

}
