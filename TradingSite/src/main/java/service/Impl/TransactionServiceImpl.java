package service.Impl;

import dao.TransactionDao;
import exceptions.MalformedObjectException;
import exceptions.TransactionNotFoundException;
import model.Transaction;
import service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    public TransactionServiceImpl(TransactionDao transactionDao)
    {
        this.transactionDao = transactionDao;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws MalformedObjectException {
        if (verifyTransactionNotNull(transaction)) {
            return transactionDao.addTransaction(transaction);
        } else {
            throw new MalformedObjectException();
        }
    }

    @Override
    public Transaction getTransaction(int transactionId) throws TransactionNotFoundException {
        if (transactionId < 1) throw new TransactionNotFoundException();
        return transactionDao.getTransaction(transactionId);
    }

    @Override
    public void deleteTransaction(int transactionId) throws TransactionNotFoundException {
        if (transactionId < 1) throw new TransactionNotFoundException();
        transactionDao.deleteTransaction(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    @Override
    public List<Transaction> getTransactionsByUser(int userId) throws TransactionNotFoundException {
        if (userId < 1) throw new TransactionNotFoundException("The user this transaction belongs to could not be found");
        return transactionDao.getTransactionsByUser(userId);
    }

    @Override
    public List<Transaction> getTransactionsByUserDescendingDateTime(int userId) throws TransactionNotFoundException {
        if (userId < 1) throw new TransactionNotFoundException("The user this transaction belongs to could not be found");

        return transactionDao.getTransactionsByUserDescendingDateTime(userId);
    }

    private boolean verifyTransactionNotNull(Transaction transaction)
    {
        if (transaction.getUserId() < 1 ||
        transaction.getSymbol() == null ||
        transaction.getSymbol().trim().equals("") ||
        transaction.getAmountTraded() < 1 ||
        transaction.getStockPriceAtPurchase() == null ||
        transaction.getStockPriceAtPurchase().compareTo(new BigDecimal(0)) <= 0 ||
        transaction.getTransactionDateTime() == null)
            return false;
        return true;
    }
}
