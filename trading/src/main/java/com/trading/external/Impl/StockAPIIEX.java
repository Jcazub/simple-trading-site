package com.trading.external.Impl;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.NotEnoughFundsException;
import com.trading.exceptions.StockNotFoundException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.external.StockAPI;
import com.trading.model.*;
import com.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.zankowski.iextrading4j.api.exception.IEXTradingException;
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
    private UserService userService;

    @Autowired
    public StockAPIIEX(TransactionService transactionService, UserStockService userStockService, UserService userService) {
        cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1_SANDBOX,
                new IEXCloudTokenBuilder()
                        .withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
                        .build());
        this.transactionService = transactionService;
        this.userStockService = userStockService;
        this.userService = userService;
    }

    @Override
    public Stock getStockInfo(String symbol) throws StockNotFoundException {

        try {
            final Quote quote = cloudClient.executeRequest(new QuoteRequestBuilder()
                    .withSymbol(symbol)
                    .build());

            return new Stock(symbol, quote.getLatestPrice());
        } catch (IEXTradingException e) {
            throw new StockNotFoundException();
        }

    }

    @Override
    public UserStock buyStock(TradeRequest tradeRequest) throws StockNotFoundException, MalformedObjectException, NotEnoughFundsException, UserNotFoundException {
        Stock stockToBeBought = getStockInfo(tradeRequest.getSymbol());

        BigDecimal purchasePrice = stockToBeBought.getPrice().multiply(new BigDecimal(tradeRequest.getAmountToBeTraded()));

        if (doesUserHaveEnoughFunds(tradeRequest, purchasePrice))
        {
            User user = tradeRequest.getUser();

            Transaction transaction = new Transaction(user.getUserId(),
                    tradeRequest.getAmountToBeTraded(), tradeRequest.getSymbol(), LocalDateTime.now(),
                    stockToBeBought.getPrice(), tradeRequest.getTransactionType());

            transactionService.addTransaction(transaction);

            UserStock boughtStock = UserStock.convertToOwnedStock(stockToBeBought, user.getUserId(),
                    tradeRequest.getAmountToBeTraded());

            updateBalance(user, purchasePrice);

            return userStockService.addStock(boughtStock);
        } else {
            throw new NotEnoughFundsException();
        }
    }

    private boolean doesUserHaveEnoughFunds(TradeRequest tradeRequest, BigDecimal totalPrice) {
        return tradeRequest.getUser().getCurrentBalance().compareTo(totalPrice) >= 0;
    }

    private void updateBalance(User user, BigDecimal amountToSubtract) throws UserNotFoundException, MalformedObjectException {
        BigDecimal currentBalance = user.getCurrentBalance();
        BigDecimal newBalance = currentBalance.subtract(amountToSubtract);
        user.setCurrentBalance(newBalance);
        userService.editUser(user);
    }
}
