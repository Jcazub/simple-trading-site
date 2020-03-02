package service;

import model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    Transaction editTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);
    void deleteTransaction(int transactionId);

    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUser(int userId);
    List<Transaction> getTransactionsByUserDescendingDateTime(int userId);
}
