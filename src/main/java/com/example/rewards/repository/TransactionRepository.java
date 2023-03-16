package com.example.rewards.repository;

import com.example.rewards.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT T FROM Transaction T WHERE customerId = :customerId and year(T.transactionDate) = :year AND month(T.transactionDate) = :month")
    List<Transaction> findAllCustomerTransactionsByDate(@Param("customerId") Long customerId, @Param("year") Integer year, @Param("month") Integer month);

}
