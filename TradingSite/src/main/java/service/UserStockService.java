package service;

import model.UserStock;

import java.util.List;

public interface UserStockService {
    UserStock addStock(UserStock stock);
    UserStock editStock(UserStock stock);
    UserStock getStock(int stockId);
    void deleteStock(int stockId);

    List<UserStock> getAllStocks();
    List<UserStock> getStocksByUser(int userId);
    List<UserStock> getStocksByUserDescendingInPrice(int userId);
}
