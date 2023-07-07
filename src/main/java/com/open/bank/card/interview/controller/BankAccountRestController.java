package com.open.bank.card.interview.controller;

import com.open.bank.card.interview.dto.AddMoneyDTO;
import com.open.bank.card.interview.dto.BankAccountBalanceDTO;
import com.open.bank.card.interview.dto.BaseDto;
import com.open.bank.card.interview.dto.IdDTO;
import com.open.bank.card.interview.service.impl.BankAccountServiceImpl;
import com.open.bank.card.interview.service.impl.BankAccountServiceStubImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank-accounts")
@Slf4j
public class BankAccountRestController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;
    @Autowired
    private BankAccountServiceStubImpl bankAccountServiceStub;

    @GetMapping("/balance")
    public BankAccountBalanceDTO getBalance(@RequestBody IdDTO idDTO) {
        logOperation(idDTO);

        try {
            return bankAccountService.getBalance(idDTO.getId());
        }
        catch (Exception e){

        }
        return new BankAccountBalanceDTO();
    }

    @GetMapping("/balance-stub")
    public BankAccountBalanceDTO getBalanceStub(@RequestBody IdDTO idDTO){
        logOperation(idDTO);
        return bankAccountServiceStub.getBalance(idDTO.getId());
    }

    @PutMapping("/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BankAccountBalanceDTO addMoney(@RequestBody AddMoneyDTO bankAccountAddMoneyDTO){
        logOperation(bankAccountAddMoneyDTO);
        return bankAccountService.addMoney(bankAccountAddMoneyDTO.getId(), bankAccountAddMoneyDTO.getSum());
    }

    private void logOperation(BaseDto baseDto){

        if(baseDto instanceof AddMoneyDTO){
            log.info("AddMoneyDto: id = {}", ((AddMoneyDTO) baseDto).getId());
        }
        else if(baseDto instanceof IdDTO){
            log.info("IdDto = {}", baseDto);
        }

    }

}
