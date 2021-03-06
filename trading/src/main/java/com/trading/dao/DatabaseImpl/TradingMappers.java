package com.trading.dao.DatabaseImpl;

import com.trading.model.Transaction;
import com.trading.model.TransactionType;
import com.trading.model.User;
import com.trading.model.UserStock;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TradingMappers {

    public static final class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int i) throws SQLException
        {
            int transactionId = rs.getInt("transactionId");
            int userId  = rs.getInt("userId");
            int amountTraded = rs.getInt("amountTraded");
            String symbol = rs.getString("symbol");
            LocalDateTime transactionDateTime = rs.getObject("transactionDateTime", LocalDateTime.class);
            BigDecimal transactionPriceAtPurchase = rs.getBigDecimal("stockPriceAtPurchase");
            TransactionType transactionType = TransactionType.valueOf(rs.getString("transactionType"));

            Transaction transaction = new Transaction(userId, amountTraded, symbol, transactionDateTime,
                    transactionPriceAtPurchase, transactionType);

            transaction.setTransactionId(transactionId);

            return transaction;
        }
    }

    public static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException
        {
            int userId = rs.getInt("userId");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String email = rs.getString("email");
            BigDecimal currentBalance = rs.getBigDecimal("currentBalance");
            String password = rs.getString("password");

            User user = new User(firstName, lastName, email, currentBalance, password);

            user.setUserId(userId);

            return user;
        }
    }

    public static final class StockMapper implements RowMapper<UserStock> {
        @Override
        public UserStock mapRow(ResultSet rs, int i) throws SQLException
        {
            int stockId = rs.getInt("stockId");
            int userId = rs.getInt("userId");
            String symbol = rs.getString("symbol");
            BigDecimal price = rs.getBigDecimal("price");
            int ownedUnits = rs.getInt("ownedUnits");

            UserStock userStock = new UserStock(symbol, price, userId, ownedUnits);

            userStock.setStockId(stockId);

            return userStock;
        }
    }
}
