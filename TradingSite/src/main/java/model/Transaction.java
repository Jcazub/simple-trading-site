package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private int transactionId, userId, amountTraded;
    private String symbol;
    private LocalDateTime transactionDateTime;
    private BigDecimal stockPriceAtPurchase;

    public Transaction(int transactionId, int userId, int amountTraded, String symbol, LocalDateTime transactionDateTime, BigDecimal stockPriceAtPurchase) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amountTraded = amountTraded;
        this.symbol = symbol;
        this.transactionDateTime = transactionDateTime;
        this.stockPriceAtPurchase = stockPriceAtPurchase;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public BigDecimal getStockPriceAtPurchase() {
        return stockPriceAtPurchase;
    }

    public int getAmountTraded() {
        return amountTraded;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAmountTraded(int amountTraded) {
        this.amountTraded = amountTraded;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public void setStockPriceAtPurchase(BigDecimal stockPriceAtPurchase) {
        this.stockPriceAtPurchase = stockPriceAtPurchase;
    }

    public BigDecimal getTotalPrice() {
        return stockPriceAtPurchase.multiply(new BigDecimal(amountTraded));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId &&
                userId == that.userId &&
                amountTraded == that.amountTraded &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(transactionDateTime, that.transactionDateTime) &&
                Objects.equals(stockPriceAtPurchase, that.stockPriceAtPurchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, userId, amountTraded, symbol, transactionDateTime, stockPriceAtPurchase);
    }
}
