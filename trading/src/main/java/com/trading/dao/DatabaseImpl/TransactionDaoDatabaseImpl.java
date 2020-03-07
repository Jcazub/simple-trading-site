package com.trading.dao.DatabaseImpl;

import com.trading.dao.TransactionDao;
import com.trading.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class TransactionDaoDatabaseImpl implements TransactionDao {

    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionDaoDatabaseImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String DELETE_TRANSACTION = "delete from transactions where transactionId = ?";
    private static final String SELECT_TRANSACTION = "select * from transactions where transactionId = ?";
    private static final String SELECT_ALL_TRANSACTION = "select * from transactions";
    private static final String SELECT_ALL_TRANSACTIONS_BY_USER = "select * from transactions where userId = ?";
    private static final String SELECT_ALL_TRANSACTIONS_BY_USER_DESCENDING_DATE_TIME = "select * from transactions " +
            "where userId = ? ORDER BY transactionDateTime DESC";

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Transaction addTransaction(Transaction transaction) {
        SqlParameterSource sps = new MapSqlParameterSource()
                .addValue("userId", transaction.getUserId())
                .addValue("amountTraded", transaction.getAmountTraded())
                .addValue("symbol", transaction.getSymbol())
                .addValue("transactionDateTime", transaction.getTransactionDateTime())
                .addValue("stockPriceAtPurchase", transaction.getStockPriceAtPurchase().doubleValue())
                .addValue("transactionType", transaction.getTransactionType().toString());

        int transactionId = new SimpleJdbcInsert(dataSource)
                .withTableName("transactions")
                .usingGeneratedKeyColumns("transactionId")
                .executeAndReturnKey(sps)
                .intValue();

        transaction.setTransactionId(transactionId);
        return transaction;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Transaction getTransaction(int transactionId) {
        return jdbcTemplate.queryForObject(SELECT_TRANSACTION, new TradingMappers.TransactionMapper(), transactionId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteTransaction(int transactionId) {
        jdbcTemplate.update(DELETE_TRANSACTION, transactionId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Transaction> getAllTransactions() {
        return jdbcTemplate.query(SELECT_ALL_TRANSACTION, new TradingMappers.TransactionMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Transaction> getTransactionsByUser(int userId) {
        return jdbcTemplate.query(SELECT_ALL_TRANSACTIONS_BY_USER, new TradingMappers.TransactionMapper(), userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Transaction> getTransactionsByUserDescendingDateTime(int userId) {
        return jdbcTemplate.query(SELECT_ALL_TRANSACTIONS_BY_USER_DESCENDING_DATE_TIME,
                new TradingMappers.TransactionMapper(), userId);
    }
}
