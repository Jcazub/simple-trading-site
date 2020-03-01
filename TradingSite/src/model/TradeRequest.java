package model;

public class TradeRequest {

    private User user;
    private int amountToBeTraded;
    private TransactionType transactionType;
    private String symbol;

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
        return symbol;
    }
}
