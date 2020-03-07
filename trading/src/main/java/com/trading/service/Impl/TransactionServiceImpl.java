package com.trading.service.Impl;

import com.trading.dao.TransactionDao;
import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.TransactionNotFoundException;
import com.trading.model.Transaction;
import com.trading.service.TransactionService;
import com.trading.utilites.VerificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    @Autowired
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
        if (VerificationHelper.isStringInvalid(transaction.getSymbol()) ||
        transaction.getAmountTraded() < 1 ||
        VerificationHelper.isBigDecimalInvalid(transaction.getStockPriceAtPurchase())||
        transaction.getTransactionDateTime() == null)
            return false;
        return true;
    }
}
