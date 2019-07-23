package com.epam.petersburg.ncr41.service;

import com.epam.petersburg.ncr41.dao.interfaces.AccountDao;
import com.epam.petersburg.ncr41.dao.impl.AccountDaoImpl;
import com.epam.petersburg.ncr41.dao.interfaces.UserDao;
import com.epam.petersburg.ncr41.dao.impl.UserDaoImpl;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdminService {

    public List<List<String>> getUsers(int currentPage, int recordPerPage) {
        List<List<String>> list = getUsers();
        return getPaginationLists(currentPage, recordPerPage, list);
    }

    private List<List<String>> getUsers() {
        UserDao userDao = new UserDaoImpl();
        AccountDao accountDao = new AccountDaoImpl();
        List<User> users = userDao.findAll();
        List<Account> accounts = accountDao.findAll();
        List<List<String>> list = new ArrayList<>();

        for (User u : users
        ) {
            List<Account> userAccounts = new ArrayList<>();
            for (Account a : accounts
            ) {
                if (u.getUserId() == a.getUserID()) {
                    userAccounts.add(a);
                }
            }

            for (Account userAccount : userAccounts) {
                List<String> list1 = new ArrayList<>();
                list1.add(u.getFirstName());
                list1.add(u.getLastName());
                list1.add(u.getEmail());
                list1.add(userAccount.getAccountID());
                list1.add(userAccount.getBalance().toString());
                list1.add(Boolean.toString(userAccount.isBlocked()));
                list.add(list1);
            }
        }
        list.sort(Comparator.comparing(o -> o.get(2)));

        return list;
    }

    public void unblockUser(String s) {
        AccountDao accountDao = new AccountDaoImpl();
        Account account = accountDao.findOne(s);
        account.setBlocked(false);
        accountDao.update(account);
    }

    public List<List<String>> filterByEmail(String email) {
        List<List<String>> lists = getUsers();
        List<List<String>> newLists = new ArrayList<>();
        for (List<String> list : lists
        ) {
            if (list.get(2).equals(email)) {
                newLists.add(list);
            }
        }
        newLists.sort(Comparator.comparing(o -> o.get(2)));

        return newLists;
    }

    public int getSize() {
        return getUsers().size();
    }

    private List<List<String>> getPaginationLists(int currentPage, int recordPerPage, List<List<String>> list) {
        int fromIndex = currentPage * recordPerPage - recordPerPage;
        int toIndex;
        if (currentPage * recordPerPage > list.size()) {
            toIndex = list.size();
        } else {
            toIndex = currentPage * recordPerPage;
        }
        list = list.subList(fromIndex, toIndex);
        return list;
    }
}
