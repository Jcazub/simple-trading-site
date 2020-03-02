package dao;

import javafx.application.Application;
import model.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class TransactionDaoTest {

    TransactionDao transactionDao;

    public TransactionDaoTest()
    {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        transactionDao = ctx.getBean("transactionDao", TransactionDao.class);
    }

    @Before
    public void setUp()
    {
        List<Transaction> transactions = transactionDao.getAllTransactions();
        for (Transaction currentTransaction : transactions)
        {
            transactionDao.deleteTransaction(currentTransaction.getTransactionId());
        }
    }

    @Test
    public void testAddGetTransaction()
    {
        Transaction transaction = PowerMockito.mock(Transaction.class);

        transactionDao.addTransaction(transaction);


    }

}