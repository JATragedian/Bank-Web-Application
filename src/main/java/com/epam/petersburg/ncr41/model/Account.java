package com.epam.petersburg.ncr41.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Account {

    private String accountID;
    private BigDecimal balance;
    private int userID;
    private boolean isBlocked;

    public Account(){

    }

    public Account(String accountID, int userID){
        this.accountID = accountID;
        this.balance = BigDecimal.valueOf(0);
        this.userID = userID;
        this.isBlocked = false;
    }

    public Account(String accountID, BigDecimal balance, int userID, boolean isBlocked) {
        this.accountID = accountID;
        this.balance = balance;
        this.userID = userID;
        this.isBlocked = isBlocked;
    }

    public String getAccountID() {
        return accountID;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getUserID() {
        return userID;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o){
      if(this == o) return true;
      if(o == null) return false;
      if(getClass() != o.getClass()) return false;
      Account acc = (Account) o;
      return getAccountID().equals(acc.getAccountID()) &&
              getBalance().equals(acc.getBalance()) &&
              getUserID() == acc.getUserID() &&
              isBlocked() == acc.isBlocked();
    }

    @Override
    public int hashCode(){
        int hash = 17;
        int prime = 31;
        hash = hash * prime + ((getAccountID() == null) ? 0 : getAccountID().hashCode());
        hash = hash * prime + ((getBalance() == null) ? 0 : getBalance().hashCode());
        hash = hash * prime + getUserID();
        hash = hash * prime + (isBlocked() ? 1 : 0);

        return hash;
    }

    @Override
    public String toString(){
        return ("AccountID: " + accountID + ", balance: " + balance + ", userID: " + userID + ", isBlocked: " + isBlocked);
    }

}

