package com.example.rewards.controller;


import com.example.rewards.dto.TransactionDTO;
import com.example.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    /**
     * Add the given transaction against the customer id. Transaction data is current server date
     * @param transaction Transaction info
     * @return success
     */
    @PostMapping("/transaction")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionDTO transaction) {
        transactionService.addTransaction(transaction);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
