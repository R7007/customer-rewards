package com.example.rewards.service;

import com.example.rewards.dto.CustomerRewardDTO;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class RewardCalculatorService {

    public static final int LEVEL1_SPENT = 50;
    public static final int LEVEL2_SPENT = 100;
    public static final int BETWEEN_50_100_POINTS = 1;
    public static final int OVER_100_POINTS = 2;

    @Autowired
    private TransactionRepository transactionRepository;

    public CustomerRewardDTO getRewardsForCustomerId(Long customerId) {


        LocalDateTime now = LocalDateTime.now();
        List<Transaction> currentMonthTransaction = transactionRepository.findAllCustomerTransactionsByDate(customerId, now.getYear(), now.getMonthValue());

        LocalDateTime previousMonth = now.minusMonths(1);
        List<Transaction> previousMonthTransaction = transactionRepository.findAllCustomerTransactionsByDate(customerId, previousMonth.getYear(), previousMonth.getMonthValue());

        LocalDateTime previousToPreviousMonth = now.minusMonths(2);
        List<Transaction> previousToPreviousMonthTransaction = transactionRepository.findAllCustomerTransactionsByDate(customerId, previousToPreviousMonth.getYear(), previousToPreviousMonth.getMonthValue());


        Long currentMonthRewardsPoints = computeRewards(currentMonthTransaction);
        Long previousMonthRewardsPoints = computeRewards(previousMonthTransaction);
        Long previousToPreviousMonthRewardsPoints = computeRewards(previousToPreviousMonthTransaction);
        Long totalRewardsPoints = currentMonthRewardsPoints + previousMonthRewardsPoints + previousToPreviousMonthRewardsPoints;

        return new CustomerRewardDTO(customerId, currentMonthRewardsPoints, previousMonthRewardsPoints, previousToPreviousMonthRewardsPoints, totalRewardsPoints);

    }

    private Long computeRewards(List<Transaction> transactions) {
        return transactions.stream().map(this::calculateRewardsOfTransaction).mapToLong(r -> r).sum();
    }

    private Long calculateRewardsOfTransaction(Transaction txn) {
        double transactionAmount = txn.getTransactionAmount();
        if (transactionAmount > LEVEL1_SPENT && transactionAmount < LEVEL2_SPENT)
            return Math.round(transactionAmount - LEVEL1_SPENT) * BETWEEN_50_100_POINTS;
        else if (transactionAmount > LEVEL2_SPENT)
            return Math.round(transactionAmount - LEVEL2_SPENT) * OVER_100_POINTS
                    + (LEVEL2_SPENT - LEVEL1_SPENT) * BETWEEN_50_100_POINTS;
        else
            return 0L;
    }
}
