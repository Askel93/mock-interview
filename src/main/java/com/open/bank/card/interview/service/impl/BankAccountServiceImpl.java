package com.open.bank.card.interview.service.impl;

import com.open.bank.card.interview.dao.BankAccountDAO;
import com.open.bank.card.interview.dto.BankAccountBalanceDTO;
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
    public BankAccountBalanceDTO addMoney(int bankAccountId, double money) {
        BankAccount bankAccount = bankAccountDAO.findById(bankAccountId)
                .orElseThrow(() -> new EntityNotFoundException(""));

        bankAccount.setBalance(bankAccount.getBalance() + money);
        bankAccount = bankAccountDAO.save(bankAccount);
        return BankAccountBalanceDTO.fromBankAccount(bankAccount);
    }

    @Override
    public BankAccountBalanceDTO getBalance(int id) {
        BankAccount bankAccount = bankAccountDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));

        BankAccountBalanceDTO dto = new BankAccountBalanceDTO();
        dto.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency() == "RUR"){
            dto.setCurrency("RUB");
        }
        else {
            dto.setCurrency(bankAccount.getCurrency());
        }

        return dto;
    }

    //instance of

}
