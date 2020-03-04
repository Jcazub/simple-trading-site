package com.trading.external;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.StockNotFoundException;
import com.trading.model.UserStock;
import com.trading.model.Stock;
import com.trading.model.TradeRequest;

public interface StockAPI {
    Stock getStockInfo(String symbol) throws StockNotFoundException;
    UserStock buyStock(TradeRequest tradeRequest) throws StockNotFoundException, MalformedObjectException;
}
