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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    public Map<Account, User> getAccountsWithClients() {
        Map<Account, User> accountUserMap = new HashMap<>();
        AccountDao accountDAO = new AccountDaoImpl();
        UserDao userDAO = new UserDaoImpl();

        List<User> listOfUsers = userDAO.findAll();
        List<Account> listOfAccounts = accountDAO.findAll();

        Map<Integer, User> userById = new HashMap<>();
        for (User user : listOfUsers) {
            userById.put(user.getUserId(), user);
        }

        for (Account account : listOfAccounts) {
            int id;
            if ((id = account.getUserID()) != 0) {
                accountUserMap.put(account, userById.get(id));
            }
        }
        return accountUserMap;
    }

    public Map<Card, Account> getCardsWithAccount(int user_id, String number) {
        CardDao cardDao = new CardDaoImpl();
        Map<Card, Account> cardsWithAccount = cardDao.findByAccountId(user_id, number);
        return cardsWithAccount;
    }
}
