package com.trading.external.Impl;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.NotEnoughFundsException;
import com.trading.exceptions.StockNotFoundException;
import com.trading.external.StockAPI;
import com.trading.model.Stock;
import com.trading.model.TradeRequest;
import com.trading.model.Transaction;
import com.trading.model.UserStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;
import com.trading.service.TransactionService;
import com.trading.service.UserStockService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public class StockAPIIEX implements StockAPI {

    private final IEXCloudClient cloudClient;
    private TransactionService transactionService;
    private UserStockService userStockService;

    @Autowired
    public StockAPIIEX(TransactionService transactionService, UserStockService userStockService) {
        cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1_SANDBOX,
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
    public UserStock buyStock(TradeRequest tradeRequest) throws StockNotFoundException, MalformedObjectException, NotEnoughFundsException {
        Stock stockToBeBought = getStockInfo(tradeRequest.getSymbol());

        BigDecimal purchasePrice = stockToBeBought.getPrice().multiply(new BigDecimal(tradeRequest.getAmountToBeTraded()));

        if (doesUserHaveEnoughFunds(tradeRequest, purchasePrice))
        {
            Transaction transaction = new Transaction(tradeRequest.getUser().getUserId(),
                    tradeRequest.getAmountToBeTraded(), tradeRequest.getSymbol(), LocalDateTime.now(),
                    stockToBeBought.getPrice(), tradeRequest.getTransactionType());

            transaction = transactionService.addTransaction(transaction);

            UserStock boughtStock = UserStock.convertToOwnedStock(stockToBeBought, tradeRequest.getUser().getUserId(),
                    transaction.getUserId());

            return userStockService.addStock(boughtStock);
        } else {
            throw new NotEnoughFundsException();
        }
    }

    private boolean doesUserHaveEnoughFunds(TradeRequest tradeRequest, BigDecimal totalPrice) {
        return tradeRequest.getUser().getCurrentBalance().compareTo(totalPrice) >= 0;
    }
}
