package com.example.rewards.service;

import com.example.rewards.dto.CustomerRewardDTO;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RewardCalculatorServiceTest {

    @InjectMocks
    RewardCalculatorService rewardCalculatorService;
    @Mock
    TransactionRepository transactionRepository;

    @Test
    public void test_BasicRewardCalculation() {

        List<Transaction> transactions = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        Transaction sampleTransaction = new Transaction();
        sampleTransaction.setCustomerId(1L);
        sampleTransaction.setTransactionAmount(120);
        sampleTransaction.setId(1L);
        sampleTransaction.setTransactionDate(Timestamp.valueOf(now));
        transactions.add(sampleTransaction);

        when(transactionRepository.findAllCustomerTransactionsByDate(anyLong(), anyInt(), anyInt())).thenReturn(transactions);

        CustomerRewardDTO rewards = rewardCalculatorService.getRewardsForCustomerId(1L);
        assertThat(rewards).isNotNull();

        //moth1 90 + month 2 90 + month 3 90 = 270
        assertThat(rewards.getTotalReward()).isEqualTo(270L);

    }

    @Test
    public void test_buildAndGetRewardOfCustomer() {

        List<Transaction> transactionList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();


        Transaction t1 = new Transaction();
        t1.setCustomerId(1L);
        t1.setTransactionAmount(110);
        t1.setId(1L);
        t1.setTransactionDate(Timestamp.valueOf(now));

        transactionList.add(t1);

        when(transactionRepository.findAllCustomerTransactionsByDate(anyLong(), anyInt(), anyInt())).thenReturn(transactionList);

        CustomerRewardDTO rewards = rewardCalculatorService.getRewardsForCustomerId(1L);
        assertThat(rewards).isNotNull();

        //70+70+70
        assertThat(rewards.getTotalReward()).isEqualTo(210L);

    }

    @Test
    public void test_buildAndGetRewardForCustomerWithNoTransactions() {
        List<Transaction> transactionList = new ArrayList<>();

        when(transactionRepository.findAllCustomerTransactionsByDate(anyLong(), anyInt(), anyInt())).thenReturn(transactionList);

        CustomerRewardDTO rewards = rewardCalculatorService.getRewardsForCustomerId(1L);
        assertThat(rewards).isNotNull();

        //0+0+0
        assertThat(rewards.getTotalReward()).isEqualTo(0L);

    }

}