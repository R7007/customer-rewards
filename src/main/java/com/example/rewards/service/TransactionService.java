package com.example.rewards.service;


import com.example.rewards.dto.TransactionDTO;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public void addTransaction(TransactionDTO transaction) {

        Transaction trans = new Transaction();

        LocalDateTime dateTime = LocalDateTime.now();
        Timestamp currentTimestamp = Timestamp.valueOf(dateTime);
        trans.setTransactionDate(currentTimestamp);
        trans.setCustomerId(transaction.getCustomerId());
        trans.setTransactionAmount(transaction.getAmount());

        repository.save(trans);
    }
}
