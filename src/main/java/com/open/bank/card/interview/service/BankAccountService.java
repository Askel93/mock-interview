package com.open.bank.card.interview.service;

import com.open.bank.card.interview.BusinessException;
import com.open.bank.card.interview.dto.BankAccountBalanceDTO;

public interface BankAccountService {
    BankAccountBalanceDTO getBalance(int bankAccountId) throws BusinessException;
    BankAccountBalanceDTO addMoney(int bankAccountId, double money);
}
