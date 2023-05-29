package com.open.bank.card.interview.dao;

import com.open.bank.card.interview.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BankAccountDAO extends JpaRepository<BankAccount, Integer> {
}
