package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.impl.AccountDaoImpl;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.User;

public enum BlockingService {
    INSTANCE;

    public String block(String accID, User user) {

        if (accID.equals("")) {
            return "ERROR: Account ID is not identified";
        }
        AccountDaoImpl accountDao = new AccountDaoImpl();
        Account account = accountDao.findOne(accID);
        if (account.getAccountID() == null) {
            return "ERROR: Account does not exist";
        }
        if (account.getUserID()==user.getUserId()){
            account.setBlocked(true);
            accountDao.update(account);
            return "SUCCESS: Account is blocked";
        } else return "ERROR: No access to account";

    }

}
