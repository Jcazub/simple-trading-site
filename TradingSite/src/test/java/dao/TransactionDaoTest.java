package dao;

import model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
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

        Transaction retrievedTransaction = transactionDao.getTransaction(transaction.getTransactionId());

        Assert.assertEquals(transaction, retrievedTransaction);
    }

    @Test
    public void testDeleteTransaction()
    {
        Transaction transaction = PowerMockito.mock(Transaction.class);
        transactionDao.addTransaction(transaction);

        Assert.assertEquals(1, transactionDao.getAllTransactions().size());

        transactionDao.deleteTransaction(transaction.getTransactionId());

        Assert.assertEquals(0, transactionDao.getAllTransactions().size());
    }

    @Test
    public void testGetAllTransactions()
    {
        Transaction firstTransaction = generateTransaction(1, 1);
        Transaction secondTransaction = generateTransaction(2, 1);

        transactionDao.addTransaction(firstTransaction);
        transactionDao.addTransaction(secondTransaction);

        Assert.assertEquals(2, transactionDao.getAllTransactions().size());
    }

    @Test
    public void testGetAllTransactionsByUser()
    {
        Transaction firstTransaction = generateTransaction(1, 1);
        Transaction secondTransaction = generateTransaction(2, 2);

        transactionDao.addTransaction(firstTransaction);
        transactionDao.addTransaction(secondTransaction);

        Assert.assertEquals(1, transactionDao.getTransactionsByUser(1));
    }

    @Test
    public void getTransactionsByUserDescendingDateTime()
    {
        Transaction firstTransaction = generateTransaction(1, 1);
        Transaction secondTransaction = generateTransaction(2, 1);
        Transaction thirdTransaction = generateTransaction(3, 1);

        transactionDao.addTransaction(firstTransaction);
        transactionDao.addTransaction(secondTransaction);
        transactionDao.addTransaction(thirdTransaction);

        List<Transaction> transactions = transactionDao.getTransactionsByUserDescendingDateTime(1);

        List<Transaction> transactionsSortedByDateTime = transactionDao.getTransactionsByUser(1);
        Collections.sort(transactionsSortedByDateTime, new TransactionDateTimeComparator());

        Assert.assertEquals(transactions, transactionsSortedByDateTime);

    }

    private Transaction generateTransaction(int transactionId, int userId)
    {
        return new Transaction(transactionId, userId, 1, "APPL",
                LocalDateTime.now(), new BigDecimal(1));
    }

    private class TransactionDateTimeComparator implements Comparator<Transaction> {
        @Override
        public int compare(Transaction thisTransaction, Transaction thatTransaction) {
            LocalDateTime thisTransactionDateTime = thisTransaction.getTransactionDateTime();
            LocalDateTime thatTransactionDateTime = thatTransaction.getTransactionDateTime();

            return thisTransactionDateTime.compareTo(thatTransactionDateTime);
        }
    }

}