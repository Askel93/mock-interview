package com.open.bank.card.interview.service.impl;

import com.open.bank.card.interview.BusinessException;
import com.open.bank.card.interview.dao.BankAccountDAO;
import com.open.bank.card.interview.dto.BankAccountBalanceDTO;
import com.open.bank.card.interview.entity.BankAccount;
import com.open.bank.card.interview.service.BankAccountService;
import com.open.bank.card.interview.service.ExternalPersonalInfoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountDAO bankAccountDAO;
    private final ExternalPersonalInfoService externalPersonalInfoService;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BankAccountBalanceDTO addMoney(int bankAccountId, double money) {
        BankAccount bankAccount = bankAccountDAO.findById(bankAccountId)
                .orElseThrow(() -> new EntityNotFoundException(""));

        bankAccount.setBalance(bankAccount.getBalance() + money);
        bankAccount = bankAccountDAO.save(bankAccount);

        saveTransactionInfo(bankAccountId, money);

        return BankAccountBalanceDTO.fromBankAccount(bankAccount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void saveTransactionInfo(int bankAccountId, double additionalSum){
        var personalInfo = externalPersonalInfoService.getPersonalInfo(bankAccountId);

        log.info("name = {} , passport = {} , bankAccountId = {}; additionalSum = {}",
                personalInfo.getName(), personalInfo.getPassport(), bankAccountId, additionalSum);

        externalPersonalInfoService.saveTransactionInfo(bankAccountId, personalInfo, additionalSum);
    }

    @Override
    @Transactional(readOnly = true)
    public BankAccountBalanceDTO getBalance(int id) throws BusinessException {
        BankAccount bankAccount = bankAccountDAO.findById(id)
                .orElseThrow(() -> new BusinessException());

        BankAccountBalanceDTO dto = new BankAccountBalanceDTO();
        dto.setBalance(bankAccount.getBalance());
        dto.setCurrency(bankAccount.getCurrency() == "RUR" ? "RUB" : bankAccount.getCurrency());

        return dto;
    }

    //TODO instance of

}
