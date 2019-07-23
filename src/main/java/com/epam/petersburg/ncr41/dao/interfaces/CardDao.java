package com.epam.petersburg.ncr41.dao.interfaces;

import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CardDao extends Dao<Card, Long> {
    @Override
    Card findOne(Long cardNumber);

    @Override
    List<Card> findAll();

    @Override
    Optional<Card> create(Card card);

    @Override
    void update(Card card);

    @Override
    void delete(Card card);

    Map<Card, Account> findByAccountId(int id, String number);
}
