package com.open.bank.card.interview.dto;

import com.open.bank.card.interview.entity.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountBalanceDTO extends BaseDto{
    public String currency;
    public Double balance;

    public static BankAccountBalanceDTO fromBankAccount(BankAccount bankAccount){
        return new BankAccountBalanceDTO(bankAccount.getCurrency(), bankAccount.getBalance());
    }
}