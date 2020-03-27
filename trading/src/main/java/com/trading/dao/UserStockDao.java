package com.trading.dao;

import com.trading.model.UserStock;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserStockDao {
    UserStock addStock(UserStock stock);
    UserStock editStock(UserStock stock);
    UserStock getStock(int stockId);
    UserStock getStockBySymbol(String symbol, int userId);
    void deleteStock(int stockId);

    List<UserStock> getAllStocks();
    List<UserStock> getStocksByUser(int userId);
    List<UserStock> getStocksByUserDescendingInPrice(int userId);
}

