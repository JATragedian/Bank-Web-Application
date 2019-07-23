package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.impl.AccountDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.CardDaoImpl;
import com.epam.petersburg.ncr41.dao.impl.UserDaoImpl;
import com.epam.petersburg.ncr41.dao.interfaces.AccountDao;
import com.epam.petersburg.ncr41.dao.interfaces.CardDao;
import com.epam.petersburg.ncr41.dao.interfaces.UserDao;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import com.epam.petersburg.ncr41.model.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistrationService {

    private User user;
    UserDao userDao = new UserDaoImpl();
    AccountDao accountDao = new AccountDaoImpl();
    CardDao cardDao = new CardDaoImpl();

    public RegistrationService(User user) {
        this.user = user;
    }

    public boolean saveUser() {
        user = userDao.create(user).orElse(new User());
        if (user.getUserId() != 0) {
            BigInteger accountNumber = getAccountNumber(accountDao);
            Account account = new Account(accountNumber.toString(), new BigDecimal("0"), user.getUserId(), false);
            account = accountDao.create(account).orElse(new Account()); //todo check
            Card card = new Card(account.getAccountID());
            cardDao.create(card); //todo check
            return true;
        } else {
            return false;
        }
    }

    private BigInteger getAccountNumber(AccountDao accountDAO) {
        List<Account> accounts = accountDAO.findAll();
        List<BigInteger> accountsNumbers = new ArrayList<>();
        for (Account a : accounts
        ) {
            accountsNumbers.add(new BigInteger(a.getAccountID()));
        }
        Collections.sort(accountsNumbers);
        return accountsNumbers.get(accountsNumbers.size() - 1).add(new BigInteger("1"));

    }
}
