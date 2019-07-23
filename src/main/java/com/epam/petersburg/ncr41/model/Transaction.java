
package com.epam.petersburg.ncr41.model;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {

    private int transactionId;
    private long cardNumber;
    private LocalDate date;
    private BigDecimal amount;
    private String targetAccountId;

    public Transaction() {}

    public Transaction(long cardNumber, BigDecimal amount, String targetAccountId) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.targetAccountId = targetAccountId;
        this.date = LocalDate.now();
    }

    public Transaction(int transactionId, long cardNumber, LocalDate date, BigDecimal amount, String targetAccountId) {
        this.transactionId = transactionId;
        this.cardNumber = cardNumber;
        this.date = date;
        this.amount = amount;
        this.targetAccountId = targetAccountId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) { this.targetAccountId = targetAccountId; }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", cardNumber=" + cardNumber +
                ", date=" + date +
                ", amount=" + amount +
                ", targetAccount=" + targetAccountId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getCardNumber() == that.getCardNumber() &&
                getDate().equals(that.getDate()) &&
                getAmount().equals(that.getAmount()) &&
                getTargetAccountId().equals(that.getTargetAccountId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getDate(), getAmount(), getTargetAccountId());
    }
}

