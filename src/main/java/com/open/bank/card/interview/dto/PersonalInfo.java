package com.open.bank.card.interview.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonalInfo {

    String name;
    String surname;
    String passport;

}
