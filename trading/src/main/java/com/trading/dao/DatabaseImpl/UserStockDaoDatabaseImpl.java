package com.trading.dao.DatabaseImpl;

import com.trading.dao.UserStockDao;
import com.trading.model.UserStock;
import com.trading.utilites.DatabaseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserStockDaoDatabaseImpl implements UserStockDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserStockDaoDatabaseImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String EDIT_STOCK = "update stocks set symbol = ?, price = ?," +
            "userId = ?, ownedUnits = ? where stockId = ?";
    private static final String DELETE_STOCK = "delete from stocks where stockId = ?";
    private static final String SELECT_STOCK = "select * from stocks where stockId = ?";
    private static final String SELECT_STOCK_BY_SYMBOL = "select * from stocks where symbol = ?";
    private static final String SELECT_ALL_STOCKS = "select * from stocks";
    private static final String SELECT_ALL_STOCKS_BY_USER = "select * from stocks where userId = ?";
    private static final String SELECT_ALL_STOCKS_BY_USER_DESCENDING_IN_PRICE = "select * from stocks where " +
            "userId = ? ORDER BY price DESC";


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserStock addStock(UserStock stock) {
        UserStock retrievedStock = getStockBySymbol(stock.getSymbol());

        if (retrievedStock == null) {
            SqlParameterSource sps = new MapSqlParameterSource()
                    .addValue("symbol", stock.getSymbol())
                    .addValue("price", stock.getTotalValue().doubleValue())
                    .addValue("userId", stock.getUserId())
                    .addValue("ownedUnits", stock.getOwnedUnits());

            int stockId = new SimpleJdbcInsert(dataSource)
                    .withTableName("stocks")
                    .usingGeneratedKeyColumns("stockId")
                    .executeAndReturnKey(sps)
                    .intValue();

            stock.setStockId(stockId);

            return stock;
        } else {
            int ownedUnits = retrievedStock.getOwnedUnits() + stock.getOwnedUnits();
            retrievedStock.setOwnedUnits(ownedUnits);

            retrievedStock.setPrice(stock.getPrice());

            editStock(retrievedStock);

            return retrievedStock;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserStock editStock(UserStock stock) {
        jdbcTemplate.update(EDIT_STOCK, stock.getSymbol(), stock.getPrice().doubleValue(),
                stock.getUserId(), stock.getOwnedUnits(), stock.getStockId());
        return stock;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserStock getStock(int stockId) {
        return jdbcTemplate.queryForObject(SELECT_STOCK, new TradingMappers.StockMapper(), stockId);
    }

    @Override
    public UserStock getStockBySymbol(String symbol) {
        return DatabaseHelper.queryForNullableObject(jdbcTemplate, SELECT_STOCK_BY_SYMBOL, new TradingMappers.StockMapper(), symbol);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteStock(int stockId) {
        jdbcTemplate.update(DELETE_STOCK, stockId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserStock> getAllStocks() {
        return jdbcTemplate.query(SELECT_ALL_STOCKS, new TradingMappers.StockMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserStock> getStocksByUser(int userId) {
        return jdbcTemplate.query(SELECT_ALL_STOCKS_BY_USER, new TradingMappers.StockMapper(), userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<UserStock> getStocksByUserDescendingInPrice(int userId) {
        return jdbcTemplate.query(SELECT_ALL_STOCKS_BY_USER_DESCENDING_IN_PRICE, new TradingMappers.StockMapper(), userId);
    }
}
