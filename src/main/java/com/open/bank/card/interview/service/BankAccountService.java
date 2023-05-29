package com.open.bank.card.interview.service;

import com.open.bank.card.interview.entity.BankAccount;

public interface BankAccountService {
    BankAccount findById(int bankAccountId);
    BankAccount addMoney(int bankAccountId, double money);
}
