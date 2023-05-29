package com.open.bank.card.interview.service.impl;

import com.open.bank.card.interview.dao.BankAccountDAO;
import com.open.bank.card.interview.entity.BankAccount;
import com.open.bank.card.interview.service.BankAccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountDAO bankAccountDAO;

    @Override
    public BankAccount addMoney(int bankAccountId, double money) {
        BankAccount bankAccount = findById(bankAccountId);
        bankAccount.setBalance(bankAccount.getBalance() + money);
        return bankAccountDAO.save(bankAccount);
    }

    @Override
    public BankAccount findById(int id) {
        return bankAccountDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));
    }

}
