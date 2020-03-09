package com.trading.model;

import java.math.BigDecimal;
import java.util.Objects;

public class TradeRequest {

    private User user;
    private int amountToBeTraded;
    private TransactionType transactionType;
    private String symbol;
    private BigDecimal totalPurchasePrice;

    public TradeRequest(User user, int amountToBeTraded, TransactionType transactionType, String symbol) {
        this.user = user;
        this.amountToBeTraded = amountToBeTraded;
        this.transactionType = transactionType;
        this.symbol = symbol;
    }

    public static TradeRequest buyRequest(User user, int amountToBeTraded, String symbol) {
        return new TradeRequest(user, amountToBeTraded, TransactionType.BUY, symbol);
    }

    public User getUser() {
        return user;
    }

    public int getAmountToBeTraded() {
        return amountToBeTraded;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getSymbol() {
        return symbol.toUpperCase();
    }

    public BigDecimal getTotalPurchasePrice() {
        return totalPurchasePrice;
    }

    public void setTotalPurchasePrice(BigDecimal totalPurchasePrice) {
        this.totalPurchasePrice = totalPurchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeRequest that = (TradeRequest) o;
        return amountToBeTraded == that.amountToBeTraded &&
                Objects.equals(user, that.user) &&
                transactionType == that.transactionType &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(totalPurchasePrice, that.totalPurchasePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, amountToBeTraded, transactionType, symbol, totalPurchasePrice);
    }
}
