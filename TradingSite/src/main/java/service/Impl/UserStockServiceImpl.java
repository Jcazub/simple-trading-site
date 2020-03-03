package service.Impl;

import dao.UserStockDao;
import exceptions.MalformedObjectException;
import exceptions.StockNotFoundException;
import exceptions.UserNotFoundException;
import model.UserStock;
import service.UserStockService;
import utilites.VerificationHelper;

import java.util.List;

public class UserStockServiceImpl implements UserStockService {

    private UserStockDao userStockDao;

    public UserStockServiceImpl(UserStockDao userStockDao)
    {
        this.userStockDao = userStockDao;
    }
    @Override
    public UserStock addStock(UserStock stock) throws MalformedObjectException {
        if (verifyStockNotNull(stock)) {
            return userStockDao.addStock(stock);
        } else {
            throw new MalformedObjectException();
        }
    }

    @Override
    public UserStock editStock(UserStock stock) throws MalformedObjectException, StockNotFoundException {
        if (!verifyStockNotNull(stock)) {
            throw new MalformedObjectException();
        } else if (stock.getStockId() < 1) {
            throw new StockNotFoundException();
        } else if (stock.getUserId() < 1) {
            throw new StockNotFoundException("The user this stock belongs to could not be found");
        }
        return userStockDao.editStock(stock);
    }

    @Override
    public UserStock getStock(int stockId) throws StockNotFoundException {
        if (stockId < 1) throw new StockNotFoundException();
        return userStockDao.getStock(stockId);
    }

    @Override
    public void deleteStock(int stockId) throws StockNotFoundException {
        if (stockId < 1) throw new StockNotFoundException();
        userStockDao.deleteStock(stockId);
    }

    @Override
    public List<UserStock> getAllStocks() {
        return userStockDao.getAllStocks();
    }

    @Override
    public List<UserStock> getStocksByUser(int userId) throws StockNotFoundException {
        if (userId < 1) throw new StockNotFoundException("The user this stock belongs to could not be found");
        return userStockDao.getStocksByUser(userId);
    }

    @Override
    public List<UserStock> getStocksByUserDescendingInPrice(int userId) throws StockNotFoundException {
        if (userId < 1) throw new StockNotFoundException("The user this stock belongs to could not be found");
        return userStockDao.getStocksByUserDescendingInPrice(userId);
    }

    private boolean verifyStockNotNull(UserStock stock)
    {
        if (VerificationHelper.isStringInvalid(stock.getSymbol()) ||
            VerificationHelper.isBigDecimalInvalid(stock.getPrice()) ||
            stock.getOwnedUnits() > -1 ||
            stock.getUserId() > -1)
                return false;
        return true;
    }
}
