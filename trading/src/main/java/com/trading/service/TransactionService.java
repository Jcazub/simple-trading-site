package com.trading.service;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.TransactionNotFoundException;
import com.trading.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction) throws MalformedObjectException;
    Transaction getTransaction(int transactionId) throws TransactionNotFoundException;
    void deleteTransaction(int transactionId) throws TransactionNotFoundException;
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUser(int userId) throws TransactionNotFoundException;
    List<Transaction> getTransactionsByUserDescendingDateTime(int userId) throws TransactionNotFoundException;
}
