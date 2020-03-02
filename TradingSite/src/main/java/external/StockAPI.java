package external;

import model.UserStock;
import model.Stock;
import model.TradeRequest;

public interface StockAPI {
    boolean verifyStock(String symbol);
    Stock getStockInfo(String symbol);
    UserStock buyStock(TradeRequest tradeRequest);
}
