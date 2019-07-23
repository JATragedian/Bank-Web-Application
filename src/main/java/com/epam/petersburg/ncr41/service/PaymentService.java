package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.impl.AccountDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.CardDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.TransactionDaoImpl;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import com.epam.petersburg.ncr41.model.Transaction;
import com.epam.petersburg.ncr41.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentService {

    private TransactionDaoImpl transactionDao = new TransactionDaoImpl();
    private AccountDaoImpl accountDao = new AccountDaoImpl();
    private CardDaoImpl cardDao = new CardDaoImpl();

    private static final Logger LOGGER = LogManager.getLogger(PaymentService.class);

    public String makePayment(String card, String initAmount, String targetAccount, User user) {

        Long cardNumber;
        BigDecimal amount;

        try {
            cardNumber = Long.parseLong(card);
            amount = BigDecimal.valueOf(Long.parseLong(initAmount));
        } catch (NumberFormatException | NullPointerException e) {
            LOGGER.error(e.getMessage(), e);
            return "ERROR: Invalid card number";
        }
        if (cardNumber == null || amount == null || targetAccount == null ||
                (amount.compareTo(BigDecimal.valueOf(Long.parseLong("0")))) == -1) {
            return "Invalid parameters for transaction";
        }

        Card payerCard;
        payerCard = cardDao.findOne(cardNumber);
        if (payerCard.getCardNumber() == 0) {
            return "ERROR: Card does not exist";
        }

        List<Account> accounts = accountDao.findByUserId(user.getUserId());
        List<Card> cards = new ArrayList<>();
        for (Account account : accounts
        ) {
            Map<Card, Account> card1 = cardDao.findByAccountId(user.getUserId(), account.getAccountID());
            cards.addAll(card1.keySet());
        }

        if (!cards.contains(payerCard)) {
            return "ERROR: No access to the card";
        }

        Account payerAccount = accountDao.findOne(payerCard.getAccountID());
        if (payerAccount.isBlocked()) {
            return "ERROR: Account is blocked";
        }

        Account receiveAccount;
        receiveAccount = accountDao.findOne(targetAccount);
        if (receiveAccount.getBalance() == null) {
            return "ERROR: Target account does not exist";
        }

        if (((payerAccount.getBalance().subtract(amount)).compareTo(BigDecimal.valueOf(Long.parseLong("0")))) == -1) {
            return "ERROR: Insufficient funds";
        }

        if (payerCard.getAccountID().equals(receiveAccount.getAccountID())) {
            return "ERROR: Operation unavailable";
        }

        payerAccount.setBalance(payerAccount.getBalance().subtract(amount));
        receiveAccount.setBalance(receiveAccount.getBalance().add(amount));
        accountDao.update(payerAccount);
        accountDao.update(receiveAccount);

        Transaction transaction = new Transaction(cardNumber, amount, targetAccount);
        transaction.setDate(LocalDate.now());
        transaction.setTransactionId(transactionDao.findAll().size() + 1);
        transactionDao.create(transaction);

        return "Payment successful";
    }

    public TransactionDaoImpl getTransactionDao() {
        return transactionDao;
    }

    public AccountDaoImpl getAccountDao() {
        return accountDao;
    }

    public CardDaoImpl getCardDao() {
        return cardDao;
    }

    public void setTransactionDao(TransactionDaoImpl transactionDao) {
        this.transactionDao = transactionDao;
    }

    public void setAccountDao(AccountDaoImpl accountDao) {
        this.accountDao = accountDao;
    }

    public void setCardDao(CardDaoImpl cardDao) {
        this.cardDao = cardDao;
    }

}
