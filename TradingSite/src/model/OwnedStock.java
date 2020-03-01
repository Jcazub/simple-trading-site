package model;

import java.math.BigDecimal;
import java.util.Objects;

public class OwnedStock extends Stock {

    private int stockId, userId, ownedUnits;

    public OwnedStock(String symbol, BigDecimal price, int availableUnits) {
        super(symbol, price, availableUnits);
    }

    public OwnedStock(String symbol, BigDecimal price, int availableUnits, int stockId, int userId, int ownedUnits) {
        super(symbol, price, availableUnits);
        this.stockId = stockId;
        this.userId = userId;
        this.ownedUnits = ownedUnits;
    }

    public static OwnedStock convertToOwnedStock(Stock stock, int stockId, int userId, int ownedUnits) {
        return new OwnedStock(stock.getSymbol(), stock.getPrice(), stock.getAvailableUnits(), stockId, userId, ownedUnits);
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

    public void setOwnedUnits(int ownedUnits) {
        this.ownedUnits = ownedUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OwnedStock that = (OwnedStock) o;
        return stockId == that.stockId &&
                userId == that.userId &&
                ownedUnits == that.ownedUnits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stockId, userId, ownedUnits);
    }
}
