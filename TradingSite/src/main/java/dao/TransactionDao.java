package dao;

import model.Transaction;

import java.util.List;

public interface TransactionDao {
    Transaction addTransaction(Transaction transaction);
    Transaction editTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);
    void deleteTransaction(int transactionId);

    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByUser(int userId);
    List<Transaction> getTransactionsByUserDescendingDateTime(int userId);
}
