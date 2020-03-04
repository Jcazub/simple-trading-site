package com.trading.model;

import java.math.BigDecimal;
import java.util.Objects;

public class UserStock extends Stock {

    private int stockId, userId, ownedUnits;

    public UserStock(String symbol, BigDecimal price, int userId, int ownedUnits) {
        super(symbol, price);
        this.userId = userId;
        this.ownedUnits = ownedUnits;
    }

    public static UserStock convertToOwnedStock(Stock stock, int userId, int ownedUnits) {
        return new UserStock(stock.getSymbol(), stock.getPrice(), userId, ownedUnits);
    }

    public int getStockId() {
        return stockId;
    }

    public int getUserId() {
        return userId;
    }

    public int getOwnedUnits() {
        return ownedUnits;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOwnedUnits(int ownedUnits) {
        this.ownedUnits = ownedUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserStock that = (UserStock) o;
        return stockId == that.stockId &&
                userId == that.userId &&
                ownedUnits == that.ownedUnits;
    }

    public BigDecimal getTotalValue()
    {
        return price.multiply(new BigDecimal(ownedUnits));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stockId, userId, ownedUnits);
    }
}
