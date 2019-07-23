package com.epam.petersburg.ncr41.dao.interfaces;

import com.epam.petersburg.ncr41.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDao extends Dao<Transaction, Integer> {

    @Override
    Transaction findOne(Integer transactionId);

    @Override
    List<Transaction> findAll();

    @Override
    Optional<Transaction> create(Transaction transaction);

    @Override
    void update(Transaction transaction);

    @Override
    void delete(Transaction transaction);
}
