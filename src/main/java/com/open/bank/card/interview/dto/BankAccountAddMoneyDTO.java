package com.open.bank.card.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountAddMoneyDTO extends BankAccountIdDTO{
    private double sum;

    public BankAccountAddMoneyDTO(Integer id) {
        super(id);
    }
}
