package com.open.bank.card.interview.controller;

import com.open.bank.card.interview.dto.AddMoneyDTO;
import com.open.bank.card.interview.dto.BankAccountBalanceDTO;
import com.open.bank.card.interview.dto.IdDTO;
import com.open.bank.card.interview.service.impl.BankAccountServiceImpl;
import com.open.bank.card.interview.service.impl.BankAccountServiceStubImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountRestController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;
    @Autowired
    private BankAccountServiceStubImpl bankAccountServiceStub;

    @GetMapping("/balance")
    public BankAccountBalanceDTO getBalance(@RequestBody IdDTO idDTO){
        return bankAccountService.getBalance(idDTO.getId());
    }

    @GetMapping("/balance-stub")
    public BankAccountBalanceDTO getBalanceStub(@RequestBody IdDTO idDTO){
        return bankAccountServiceStub.getBalance(idDTO.getId());
    }

    @PatchMapping("/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BankAccountBalanceDTO addMoney(@RequestBody AddMoneyDTO bankAccountAddMoneyDTO){
        return bankAccountService.addMoney(bankAccountAddMoneyDTO.getId(), bankAccountAddMoneyDTO.getSum());
    }

}
