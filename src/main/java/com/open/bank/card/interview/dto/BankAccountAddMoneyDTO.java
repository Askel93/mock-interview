package com.open.bank.card.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BankAccountAddMoneyDTO {
    private Integer id;
    private double sum;
}
