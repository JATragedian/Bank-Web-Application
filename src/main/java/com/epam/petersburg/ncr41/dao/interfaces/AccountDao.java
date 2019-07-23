package com.epam.petersburg.ncr41.dao.interfaces;

import com.epam.petersburg.ncr41.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDao extends Dao<Account, String> {


    @Override
    Account findOne(String accountNumber);

    @Override
    List<Account> findAll();

    @Override
    Optional<Account> create(Account account);

    @Override
    void update(Account account);

    @Override
    void delete(Account account);

    List<Account> findByUserId(int id);
}
