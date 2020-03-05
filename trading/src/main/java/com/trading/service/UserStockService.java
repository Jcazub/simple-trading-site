package com.trading.service;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.StockNotFoundException;
import com.trading.model.UserStock;

import java.util.List;

public interface UserStockService {
    UserStock addStock(UserStock stock) throws MalformedObjectException;
    UserStock editStock(UserStock stock) throws MalformedObjectException, StockNotFoundException;
    UserStock getStock(int stockId) throws StockNotFoundException;
    void deleteStock(int stockId) throws StockNotFoundException;

    List<UserStock> getAllStocks();
    List<UserStock> getStocksByUser(int userId) throws StockNotFoundException;
    List<UserStock> getStocksByUserDescendingInPrice(int userId) throws StockNotFoundException;
}
