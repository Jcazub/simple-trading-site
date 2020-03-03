package dao.DatabaseImpl;

import model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TradingMappers {

    public static final class TransactionMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int i) throws SQLException {
            int transactionId = rs.getInt("transactionId");
            int userId  = rs.getInt("userId");
            int amountTraded = rs.getInt("amountTraded");
            String symbol = rs.getString("symbol");
            LocalDateTime transctionDateTime = rs.getObject("transactionDateTime", LocalDateTime.class);
            BigDecimal transactionPriceAtPurchase = rs.getBigDecimal("stockPriceAtPurchase");

            return new Transaction(transactionId, userId, amountTraded, symbol, transctionDateTime,
                    transactionPriceAtPurchase);
        }
    }
}
