package model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal currentBalance;
    private List<Stock> stocksOwned;
    private List<Transaction> transactionsCompleted;

    public User(int userId, String firstName, String lastName, String email, BigDecimal currentBalance, List<Stock> stocksOwned, List<Transaction> transactionsCompleted) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.currentBalance = currentBalance;
        this.stocksOwned = stocksOwned;
        this.transactionsCompleted = transactionsCompleted;
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

    public List<Stock> getStocksOwned() {
        return stocksOwned;
    }

    public List<Transaction> getTransactionsCompleted() {
        return transactionsCompleted;
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
                Objects.equals(currentBalance, user.currentBalance) &&
                Objects.equals(stocksOwned, user.stocksOwned) &&
                Objects.equals(transactionsCompleted, user.transactionsCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, currentBalance, stocksOwned, transactionsCompleted);
    }
}
