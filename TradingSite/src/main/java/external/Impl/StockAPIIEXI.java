package external.Impl;

import exceptions.MalformedObjectException;
import exceptions.StockNotFoundException;
import external.StockAPI;
import model.Stock;
import model.TradeRequest;
import model.Transaction;
import model.UserStock;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;
import service.TransactionService;
import service.UserStockService;

import java.time.LocalDateTime;

public class StockAPIIEXI implements StockAPI {

    private final IEXCloudClient cloudClient;
    private TransactionService transactionService;
    private UserStockService userStockService;

    public StockAPIIEXI(TransactionService transactionService, UserStockService userStockService) {
        cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1,
                new IEXCloudTokenBuilder()
                        .withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
                        .build());
        this.transactionService = transactionService;
        this.userStockService = userStockService;
    }

    @Override
    public Stock getStockInfo(String symbol) throws StockNotFoundException {
        final Quote quote = cloudClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(symbol)
                .build());

        if (quote == null) throw new StockNotFoundException();

        return new Stock(symbol, quote.getLatestPrice());
    }

    @Override
    public UserStock buyStock(TradeRequest tradeRequest) throws StockNotFoundException, MalformedObjectException {
        Stock stockToBeBought = getStockInfo(tradeRequest.getSymbol());

        Transaction transaction = new Transaction(tradeRequest.getUser().getUserId(),
                tradeRequest.getAmountToBeTraded(), tradeRequest.getSymbol(), LocalDateTime.now(),
                stockToBeBought.getPrice(), tradeRequest.getTransactionType());

        transaction = transactionService.addTransaction(transaction);

        UserStock boughtStock = UserStock.convertToOwnedStock(stockToBeBought, tradeRequest.getUser().getUserId(),
                transaction.getUserId());

        return userStockService.addStock(boughtStock);
    }
}
