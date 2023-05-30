package com.open.bank.card.interview.service;

import com.open.bank.card.interview.dto.BankAccountBalanceDTO;

public interface BankAccountService {
    BankAccountBalanceDTO getBalance(int bankAccountId);
    BankAccountBalanceDTO addMoney(int bankAccountId, double money);
}
