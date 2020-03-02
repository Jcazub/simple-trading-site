package dao;

import model.UserStock;

import java.util.List;

public interface UserStockDao {
    UserStock addStock(UserStock stock);
    UserStock editStock(UserStock stock);
    UserStock getStock(int stockId);
    void deleteStock(int stockId);

    List<UserStock> getAllStocks();
    List<UserStock> getStocksByUser(int userId);
    List<UserStock> getStocksByUserDescendingInPrice(int userId);
}

