package external;

import exceptions.MalformedObjectException;
import exceptions.StockNotFoundException;
import model.UserStock;
import model.Stock;
import model.TradeRequest;

public interface StockAPI {
    Stock getStockInfo(String symbol) throws StockNotFoundException;
    UserStock buyStock(TradeRequest tradeRequest) throws StockNotFoundException, MalformedObjectException;
}
