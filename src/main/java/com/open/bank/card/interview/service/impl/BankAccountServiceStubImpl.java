package com.open.bank.card.interview.service.impl;

import com.open.bank.card.interview.dto.BankAccountBalanceDTO;
import com.open.bank.card.interview.service.BankAccountService;
import org.springframework.stereotype.Component;

@Component
public class BankAccountServiceStubImpl implements BankAccountService {

    @Override
    public BankAccountBalanceDTO getBalance(int id) {
        return new BankAccountBalanceDTO();
    }

    @Override
    public BankAccountBalanceDTO addMoney(int bankAccountId, double money) {
        throw new UnsupportedOperationException();
    }

}
