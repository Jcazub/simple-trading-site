package com.trading.dao;

import com.trading.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionDao {
    Transaction addTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);
    void deleteTransaction(int transactionId);

    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUser(int userId);
    List<Transaction> getTransactionsByUserDescendingDateTime(int userId);
}
