package com.open.bank.card.interview.service;

import com.open.bank.card.interview.dto.PersonalInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ExternalPersonalInfoService {

    // just example of return value
    public PersonalInfo getPersonalInfo(Integer bankAccountId){
        return PersonalInfo.builder()
                .name("Mikel")
                .surname("Korleone")
                .passport("2839 43")
                .build();
    }

    public void saveTransactionInfo(Integer bankAccountId, PersonalInfo personalInfo, double additionalSum){
        //some operations
    }

}
