package com.open.bank.card.interview.controller;

import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@RequestMapping("/api/bank-accounts")
public class PersonRestController {

    private final static String USERNAME = "admin";
    private final static String PASSWORD = "m7Ds23R1B!I";
    private final static String DB_NAME = "IOT_MGMT_SYSTEM";

    @GetMapping("/owner")
    public Integer getOwnerId(@RequestParam String bankAccountId) throws SQLException {

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

}
