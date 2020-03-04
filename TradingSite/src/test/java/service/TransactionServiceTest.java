package service;

import dao.TransactionDao;
import exceptions.MalformedObjectException;
import exceptions.TransactionNotFoundException;
import model.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TransactionServiceTest {

    TransactionService transactionService;
    TransactionDao transactionDao;
    Transaction transaction;

    public TransactionServiceTest()
    {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        transactionService = ctx.getBean("transactionService", TransactionService.class);

        initializeMockTransactionDao();
    }

    @Before
    public void setUp() throws TransactionNotFoundException {
        List<Transaction> transactions = transactionService.getAllTransactions();
        for (Transaction currentTransaction : transactions)
        {
            transactionService.deleteTransaction(currentTransaction.getTransactionId());
        }
    }

    @Test
    public void testAddGetTransactionSuccess() throws MalformedObjectException {
        Transaction transaction = PowerMockito.mock(Transaction.class);
        transactionService.addTransaction(transaction);
    }

    @Test(expected = Exception.class)
    public void testAddGetTransactionException() throws Exception
    {
        Transaction transaction = PowerMockito.mock(Transaction.class);
        PowerMockito.when(transaction.getSymbol()).thenReturn(null);
        transactionService.addTransaction(transaction);
    }


    @Test
    public void testDeleteTransactionSuccess()
    {

    }

    private void initializeMockTransactionDao()
    {
        transactionDao = PowerMockito.mock(TransactionDao.class);
        transaction = PowerMockito.mock(Transaction.class);

        PowerMockito.when(transactionDao.addTransaction(Mockito.any())).thenReturn(transaction);
        PowerMockito.when(transactionDao.getAllTransactions()).thenReturn(new ArrayList<>());
        PowerMockito.when(transactionDao.getTransaction(Mockito.anyInt())).thenReturn(transaction);
        PowerMockito.when(transactionDao.getTransactionsByUser(Mockito.anyInt())).thenReturn(new ArrayList<>());
        PowerMockito.when(transactionDao.getTransactionsByUserDescendingDateTime(Mockito.anyInt()));

    }
}