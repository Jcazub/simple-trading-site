package com.trading.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Stock {

    protected String symbol;
    protected BigDecimal price;

    public Stock(String symbol, BigDecimal price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol.toUpperCase();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(symbol, stock.symbol) &&
                Objects.equals(price, stock.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price);
    }
}
