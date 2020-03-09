package com.trading.service.Impl;

import com.trading.dao.UserStockDao;
import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.StockNotFoundException;
import com.trading.model.UserStock;
import com.trading.service.UserStockService;
import com.trading.utilites.VerificationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStockServiceImpl implements UserStockService {

    private UserStockDao userStockDao;

    @Autowired
    public UserStockServiceImpl(UserStockDao userStockDao)
    {
        this.userStockDao = userStockDao;
    }
    @Override
    public UserStock addStock(UserStock stock) throws MalformedObjectException {
        if (isStockMalformed(stock)) throw new MalformedObjectException();
        return userStockDao.addStock(stock);
    }

    @Override
    public UserStock editStock(UserStock stock) throws MalformedObjectException, StockNotFoundException {
        if (stock.getStockId() < 1) throw new StockNotFoundException();
        else if (stock.getUserId() < 1) throw new StockNotFoundException("The user this stock belongs to could not be found");
        else if (isStockMalformed(stock)) throw new MalformedObjectException();
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

    private boolean isStockMalformed(UserStock stock)
    {
        if (VerificationHelper.isStringInvalid(stock.getSymbol()) ||
            VerificationHelper.isBigDecimalInvalid(stock.getPrice()) ||
            stock.getOwnedUnits() < -1 ||
            stock.getUserId() < -1)
                return true;
        return false;
    }
}
