package com.epam.petersburg.ncr41.dao.impl;

import com.epam.petersburg.ncr41.dao.interfaces.TransactionDao;
import com.epam.petersburg.ncr41.dao.dbconnection.MyDataSource;
import com.epam.petersburg.ncr41.model.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDaoImpl implements TransactionDao {

    public static final String SELECT_ALL_TRANSACTIONS = "SELECT * FROM transactions";

    public static final String SELECT_TRANSACTION_BY_ID = SELECT_ALL_TRANSACTIONS + " WHERE transaction_id=?";

    public static final String INSERT_NEW_TRANSACTION = "INSERT INTO transactions " +
            "(card_number, date, amount, target_account) " +
            "VALUES (?, ?, ?, ?);";
    private static final Logger LOGGER = LogManager.getLogger(TransactionDaoImpl.class);

    @Override
    public Transaction findOne(Integer transactionId) {
        Transaction transaction = new Transaction();


        try (Connection connection = MyDataSource.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTION_BY_ID)) {

            preparedStatement.setInt(1, transactionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
                transaction.setCardNumber(resultSet.getLong("card_number"));
                transaction.setDate(resultSet.getDate("date").toLocalDate());
                transaction.setAmount(resultSet.getBigDecimal("amount"));
                transaction.setTargetAccountId(resultSet.getString("target_account"));
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactionList = new ArrayList<>();

        try (Connection connection = MyDataSource.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL_TRANSACTIONS);

            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
                transaction.setCardNumber(resultSet.getLong("card_number"));
                transaction.setDate(resultSet.getDate("date").toLocalDate());
                transaction.setAmount(resultSet.getBigDecimal("amount"));
                transaction.setTargetAccountId(resultSet.getString("target_account"));
                transactionList.add(transaction);
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return transactionList;
    }

    @Override
    public Optional<Transaction> create(Transaction transaction) {


        try (Connection connection = MyDataSource.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_TRANSACTION);) {

            preparedStatement.setLong(1, transaction.getCardNumber());
            preparedStatement.setDate(2, Date.valueOf(transaction.getDate()));
            preparedStatement.setBigDecimal(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getTargetAccountId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        throw new java.lang.UnsupportedOperationException("Update query is not supported for transactions.");
    }

    @Override
    public void delete(Transaction transaction) {
        throw new java.lang.UnsupportedOperationException("Delete query is not supported for transactions.");
    }
}
