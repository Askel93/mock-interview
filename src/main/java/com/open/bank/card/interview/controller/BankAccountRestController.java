package com.open.bank.card.interview.controller;

import com.open.bank.card.interview.dto.BankAccountAddMoneyDTO;
import com.open.bank.card.interview.dto.BankAccountBalanceDTO;
import com.open.bank.card.interview.dto.BankAccountIdDTO;
import com.open.bank.card.interview.entity.BankAccount;
import com.open.bank.card.interview.service.impl.BankAccountServiceImpl;
import com.open.bank.card.interview.service.impl.BankAccountServiceLegacyImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountRestController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;
    @Autowired
    private BankAccountServiceLegacyImpl bankAccountServiceLegacy;

    private final static String USERNAME = "admin";
    private final static String PASSWORD = "m7Ds23R1B!I";
    private final static String DB_NAME = "IOT_MGMT_SYSTEM";

    @GetMapping("/account")
    public Integer getOwnerId(@RequestParam Integer bankAccountId) throws SQLException {

        Integer retValue = null;
        Connection connect = DriverManager
                .getConnection("jdbc:mysql://localhost:3000/" + DB_NAME  + "?useSSL=false",USERNAME, PASSWORD);
        Statement statement = connect.createStatement();

        String query = "SELECT * FROM BANK_ACCOUNT WHERE id='" + bankAccountId + "'";

        ResultSet resultSet = statement.executeQuery(query);

        if(resultSet != null)
            resultSet.next();
        retValue = resultSet.getInt(1);

        resultSet.close();
        statement.close();
        connect.close();

        return retValue;
    }

    @GetMapping("/balance")
    public BankAccountBalanceDTO getBalance(@RequestBody BankAccountIdDTO bankAccountIdDTO){

        BankAccount bankAccount = getServiceResult(bankAccountIdDTO);

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

    private BankAccount getServiceResult(BankAccountIdDTO dto){
        if(dto instanceof BankAccountAddMoneyDTO){
            var trueDto = (BankAccountAddMoneyDTO) dto;
            return bankAccountService.addMoney(trueDto.getId(), trueDto.getSum());
        }
        else if (dto instanceof BankAccountIdDTO){
            return bankAccountServiceLegacy.findById(dto.getId());
        }
        else return null;
    }

    @PatchMapping("/balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BankAccountBalanceDTO addMoney(@RequestBody BankAccountAddMoneyDTO bankAccountAddMoneyDTO){

        BankAccount bankAccount = getServiceResult(bankAccountAddMoneyDTO);
        return BankAccountBalanceDTO.fromBankAccount(bankAccount);

    }

}
