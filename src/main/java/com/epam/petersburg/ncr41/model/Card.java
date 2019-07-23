package com.epam.petersburg.ncr41.model;

import java.util.Objects;

public class Card {
    private long cardNumber;
    private String accountID;

    public Card() {
    }

    public Card(String accountID) {
        this.accountID = accountID;
    }

    public Card(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Card(long cardNumber, String account) {
        this.cardNumber = cardNumber;
        this.accountID = account;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getCardNumber() == card.getCardNumber() &&
                Objects.equals(getAccountID(), card.getAccountID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getAccountID());
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNumber +
                ", accountID=" + accountID +
                '}';
    }
}
