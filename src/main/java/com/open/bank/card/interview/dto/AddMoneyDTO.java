package com.open.bank.card.interview.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMoneyDTO extends IdDTO {
    public double sum;

    public AddMoneyDTO(Integer id) {
        super(id);
    }
}
