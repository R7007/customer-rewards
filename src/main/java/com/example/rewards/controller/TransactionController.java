package com.example.rewards.controller;


import com.example.rewards.dto.CustomerRewardDTO;
import com.example.rewards.dto.TransactionDTO;
import com.example.rewards.model.Transaction;
import com.example.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transaction) {
        transactionService.addTransaction(transaction);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
