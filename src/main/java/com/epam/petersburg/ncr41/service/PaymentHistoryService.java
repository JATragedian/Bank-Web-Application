package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.impl.AccountDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.CardDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.TransactionDaoImpl;
import com.epam.petersburg.ncr41.dao.interfaces.AccountDao;
import com.epam.petersburg.ncr41.dao.interfaces.CardDao;
import com.epam.petersburg.ncr41.dao.interfaces.TransactionDao;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import com.epam.petersburg.ncr41.model.Transaction;
import com.epam.petersburg.ncr41.model.User;

import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryService {

    User user;
    private AccountDao accountDao = new AccountDaoImpl();
    private CardDao cardDao = new CardDaoImpl();
    private TransactionDao transactionDao = new TransactionDaoImpl();

    public PaymentHistoryService(User user) {
        this.user = user;
    }

    public List<Transaction> getUsersTransactions() {
        List<Account> accountOfCurrentUser = getAllAccountsForCurrentUser();
        List<Card> cardsOfCurrentUser = new ArrayList<>();
        for (Account account : accountOfCurrentUser) {
            cardsOfCurrentUser.addAll(getAllCardsForAccount(account));
        }

        List<Transaction> transactionsOfCurrentUser = new ArrayList<>();
        for (Card card : cardsOfCurrentUser) {
            transactionsOfCurrentUser.addAll(getAllTransactionsForCard(card));
        }

        return transactionsOfCurrentUser;
    }

    private List<Account> getAllAccountsForCurrentUser() {
        List<Account> accountList = accountDao.findAll();
        List<Account> accountsOfCurrentUser = new ArrayList<>();
        for (Account account : accountList) {
            if (account.getUserID() == user.getUserId()) {
                accountsOfCurrentUser.add(account);
            }
        }

        return accountsOfCurrentUser;
    }

    private List<Card> getAllCardsForAccount(Account account) {
        List<Card> cardList = cardDao.findAll();
        List<Card> cardListOfCurrentAccount = new ArrayList<>();
        for (Card card : cardList) {
            if (card.getAccountID() != null) {
                if (card.getAccountID().equals(account.getAccountID())) {
                    cardListOfCurrentAccount.add(card);
                }
            }
        }

        return cardListOfCurrentAccount;
    }

    private List<Transaction> getAllTransactionsForCard(Card card) {
        List<Transaction> transactionList = transactionDao.findAll();
        List<Transaction> transactionsForCurrentCard = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            if (transaction.getCardNumber() == card.getCardNumber()) {
                transactionsForCurrentCard.add(transaction);
            }
        }

        return  transactionsForCurrentCard;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public CardDao getCardDao() {
        return cardDao;
    }

    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public TransactionDao getTransactionDao() {
        return transactionDao;
    }

    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
}
