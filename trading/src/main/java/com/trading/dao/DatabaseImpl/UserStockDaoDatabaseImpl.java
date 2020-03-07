package com.trading.dao.DatabaseImpl;

import com.trading.dao.UserStockDao;
import com.trading.model.UserStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserStockDaoDatabaseImpl implements UserStockDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserStockDaoDatabaseImpl(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String INSERT_STOCK = "insert into db.stocks (symbol, price, userId, " +
            "ownedUnits) values (?,?,?,?)";
    private static final String EDIT_STOCK = "update db.stocks set symbol = ?, price = ?," +
            "userId = ?, ownedUnits = ? where stockId = ?";
    private static final String DELETE_STOCK = "delete from db.stocks where stockId = ?";
    private static final String SELECT_STOCK = "select * from db.stocks where stockId = ?";
    private static final String SELECT_ALL_STOCKS = "select * from db.stocks";
    private static final String SELECT_ALL_STOCKS_BY_USER = "select * from db.stocks where userId = ?";
    private static final String SELECT_ALL_STOCKS_BY_USER_DESCENDING_IN_PRICE = "select * from db.stocks where " +
            "userId = ? ORDER BY price DESC";


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserStock addStock(UserStock stock) {
        jdbcTemplate.update(INSERT_STOCK, stock.getSymbol(), stock.getPrice().doubleValue(),
                stock.getUserId(), stock.getOwnedUnits());
        int stockId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        stock.setStockId(stockId);
        return stock;
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
