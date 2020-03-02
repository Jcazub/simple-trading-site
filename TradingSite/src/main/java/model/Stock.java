package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Stock {

    protected String symbol;
    protected BigDecimal price;
    protected int availableUnits;

    public Stock(String symbol, BigDecimal price, int availableUnits) {
        this.symbol = symbol;
        this.price = price;
        this.availableUnits = availableUnits;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return availableUnits == stock.availableUnits &&
                Objects.equals(symbol, stock.symbol) &&
                Objects.equals(price, stock.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price, availableUnits);
    }
}
