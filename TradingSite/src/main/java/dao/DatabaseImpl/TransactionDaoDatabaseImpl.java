package dao.DatabaseImpl;

import dao.TransactionDao;
import model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

public class TransactionDaoDatabaseImpl implements TransactionDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionDaoDatabaseImpl(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String INSERT_TRANSACTION = "insert into db.transactions (userId, " +
            "amountTraded, symbol, transactionDateTime, stockPriceAtPurchase) values (?,?,?,?,?)";
    private static final String DELETE_TRANSACTION = "delete from db.transactions where transactionId = ?";
    private static final String SELECT_TRANSACTION = "select * from db.transactions where transactionId = ?";
    private static final String SELECT_ALL_TRANSACTION = "select * from db.transactions";
    private static final String SELECT_ALL_TRANSACTIONS_BY_USER = "select * from transactions where userId = ?";
    private static final String SELECT_ALL_TRANSACTIONS_BY_USER_DESCENDING_DATE_TIME = "select * from db.transactions" +
            "where userId = ? ORDER BY transactionDateTime DESC";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Transaction addTransaction(Transaction transaction) {
        jdbcTemplate.update(INSERT_TRANSACTION, transaction.getUserId(), transaction.getAmountTraded(),
                transaction.getSymbol(), transaction.getTransactionDateTime(), transaction.getStockPriceAtPurchase());
        int transactionId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        transaction.setTransactionId(transactionId);
        return transaction;
    }

    @Override
    public Transaction getTransaction(int transactionId) {
        return null;
    }

    @Override
    public void deleteTransaction(int transactionId) {
        jdbcTemplate.update(DELETE_TRANSACTION, transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = jdbcTemplate.query(SELECT_ALL_TRANSACTION, new )
    }

    @Override
    public List<Transaction> getTransactionsByUser(int userId) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByUserDescendingDateTime(int userId) {
        return null;
    }
}
