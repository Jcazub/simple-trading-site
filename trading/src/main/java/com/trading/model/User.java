package com.trading.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BigDecimal currentBalance;
    private List<UserStock> stocksOwned;
    private List<Transaction> transactionsCompleted;

    public User(String firstName, String lastName, String email, BigDecimal currentBalance, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.currentBalance = currentBalance;
        this.password = password;
        this.stocksOwned = new ArrayList<>();
        this.transactionsCompleted = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public List<UserStock> getStocksOwned() {
        return stocksOwned;
    }

    public List<Transaction> getTransactionsCompleted() {
        return transactionsCompleted;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setStocksOwned(List<UserStock> stocksOwned) {
        this.stocksOwned = stocksOwned;
    }

    public void setTransactionsCompleted(List<Transaction> transactionsCompleted) {
        this.transactionsCompleted = transactionsCompleted;
    }

    public BigDecimal getTotalPorfolioValue() {
        BigDecimal totalValue = new BigDecimal(0);
        for (UserStock currentUserStock : getStocksOwned()) {
            totalValue = totalValue.add(currentUserStock.getTotalValue());
        }
        return totalValue;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                currentBalance.compareTo(user.currentBalance) == 0 &&
                Objects.equals(stocksOwned, user.stocksOwned) &&
                Objects.equals(transactionsCompleted, user.transactionsCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, password, currentBalance, stocksOwned, transactionsCompleted);
    }
}
