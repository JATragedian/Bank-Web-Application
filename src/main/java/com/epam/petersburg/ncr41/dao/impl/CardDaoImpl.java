package com.epam.petersburg.ncr41.dao.impl;


import com.epam.petersburg.ncr41.dao.interfaces.CardDao;
import com.epam.petersburg.ncr41.dao.dbconnection.DataSource;
import com.epam.petersburg.ncr41.dao.dbconnection.MyDataSource;
import com.epam.petersburg.ncr41.model.Account;
import com.epam.petersburg.ncr41.model.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class CardDaoImpl implements CardDao {

    private static final String SELECT_BY_ID = "SELECT * FROM cards WHERE card_number = ?";
    private static final String SELECT_ALL = "SELECT * FROM cards";
    private static final String UPDATE = "UPDATE cards SET account_id = ? WHERE card_number = ? RETURNING card_number";


    private DataSource dataSource = MyDataSource.INSTANCE;
    private static final Logger LOGGER = LogManager.getLogger(CardDaoImpl.class);

    @Override
    public Card findOne(Long cardNumber) {
        Card card = new Card();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setString(1, String.valueOf(cardNumber));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                card.setCardNumber(resultSet.getLong("card_number"));
                card.setAccountID(resultSet.getString("account_id"));
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return card;
    }

    @Override
    public List<Card> findAll() {
        List<Card> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            cards = getCards(cards, connection);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return cards;
    }

    private List<Card> getCards(List<Card> cards, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);

        while (resultSet.next()) {
            Card card = new Card();
            card.setCardNumber(resultSet.getLong("card_number"));
            card.setAccountID(resultSet.getString("account_id"));
            cards.add(card);
        }
        resultSet.close();
        statement.close();
        return cards;
    }

    @Override
    public Optional<Card> create(Card card) {
        List<Card> cards = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            cards = getCards(cards, connection);

            for (int i = 0; i < cards.size(); i++) {
                Card freeCard = cards.get(i);
                if (freeCard.getAccountID() == null) {
                    writeAccountNumber(card, freeCard, connection);
                    break;
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(card);
    }

    private void writeAccountNumber(Card card, Card freeCard, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, card.getAccountID());
        preparedStatement.setString(2, String.valueOf(freeCard.getCardNumber()));
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            card.setCardNumber(resultSet.getLong("card_number"));
        }
        resultSet.close();
        preparedStatement.close();
    }

    @Override
    public void update(Card card) {
        LOGGER.error("Attempt to update card data", new UnsupportedOperationException("Cards can't be updated"));
        throw new UnsupportedOperationException("Cards can't be updated");
    }


    @Override
    public void delete(Card card) {
        LOGGER.error("Attempt to delete card", new UnsupportedOperationException("Cards can't be deleted"));
        throw new UnsupportedOperationException("Cards can't be deleted");
    }

    @Override
    public Map<Card, Account> findByAccountId(int id, String number) {
        Map<Card, Account> cardAccountMap = new HashMap<>();
        String sqlQuery = "SELECT * FROM accounts INNER JOIN cards " +
                "ON cards.account_id = accounts.account_id WHERE user_id=?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (number == null||"".equals(number)||resultSet.getString("account_id").equals(number)) {
                    cardAccountMap.put(new Card(Long.valueOf(resultSet.getString("card_number")),
                                    resultSet.getString("account_id")),
                            new Account(resultSet.getString("account_id"),
                                    resultSet.getBigDecimal("balance"),
                                    resultSet.getInt("user_id"),
                                    resultSet.getBoolean("isblocked")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Invalid card number!");
        }
        return cardAccountMap;
    }
}
