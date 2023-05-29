package com.open.bank.card.interview.dto;

import com.open.bank.card.interview.entity.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BankAccountBalanceDTO {
    private String currency;
    private Double balance;

    public static BankAccountBalanceDTO fromBankAccount(BankAccount bankAccount){
        return new BankAccountBalanceDTO(bankAccount.getCurrency(), bankAccount.getBalance());
    }
}