package com.epam.petersburg.ncr41.dao.impl;

import com.epam.petersburg.ncr41.dao.interfaces.AccountDao;
import com.epam.petersburg.ncr41.dao.dbconnection.MyDataSource;
import com.epam.petersburg.ncr41.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AccountDaoImpl implements AccountDao {

    public static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT * FROM accounts WHERE account_id=?";
    public static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT * FROM accounts";
    public static final String SQL_INSERT_NEW_ACCOUNT = "INSERT INTO accounts (account_id, balance, user_id, isblocked) VALUES(?, ?, ?, ?) RETURNING account_id";
    public static final String SQL_UPDATE_ACCOUNT = "UPDATE accounts SET balance = ?, user_id = ?, isblocked = ? WHERE account_id = ?";
    public static final String SQL_DELETE_ACCOUNT = "DELETE FROM accounts WHERE account_id = ?";

    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);

    public Account findOne(String accountID) {

        Account acc = new Account();

        try (Connection con = MyDataSource.INSTANCE.getConnection();
             PreparedStatement st = con.prepareStatement(SQL_SELECT_ACCOUNT_BY_ID)) {
            st.setString(1, accountID);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                acc.setAccountID(resultSet.getString(1));
                acc.setBalance(resultSet.getBigDecimal(2));
                acc.setUserID(resultSet.getInt(3));
                acc.setBlocked(resultSet.getBoolean(4));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return acc;
    }

    @Override
    public List<Account> findAll() {
        List<Account> list = new ArrayList<>();

        try (Connection con = MyDataSource.INSTANCE.getConnection();
             PreparedStatement st = con.prepareStatement(SQL_SELECT_ALL_ACCOUNTS)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                list.add(new Account(resultSet.getString(1),
                        resultSet.getBigDecimal(2),
                        resultSet.getInt(3),
                        resultSet.getBoolean(4)
                ));
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Optional<Account> create(Account acc) {

        try (Connection con = MyDataSource.INSTANCE.getConnection();
             PreparedStatement st = con.prepareStatement(SQL_INSERT_NEW_ACCOUNT)) {
            st.setString(1, acc.getAccountID());
            st.setBigDecimal(2, acc.getBalance());
            st.setInt(3, acc.getUserID());
            st.setBoolean(4, acc.isBlocked());
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                acc.setAccountID(resultSet.getString(1));
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(acc);
    }

    @Override
    public void update(Account acc) {

        try (Connection con = MyDataSource.INSTANCE.getConnection();
             PreparedStatement st = con.prepareStatement(SQL_UPDATE_ACCOUNT)) {
            st.setBigDecimal(1, acc.getBalance());
            st.setInt(2, acc.getUserID());
            st.setBoolean(3, acc.isBlocked());
            st.setString(4, acc.getAccountID());
            st.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public void delete(Account acc) {

        try (Connection con = MyDataSource.INSTANCE.getConnection();
             PreparedStatement st = con.prepareStatement(SQL_DELETE_ACCOUNT)) {
            st.setString(1, acc.getAccountID());
            st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Account> findByUserId(int id) {
        List<Account> list = new ArrayList<>();

        try (Connection con = MyDataSource.INSTANCE.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM accounts WHERE user_id=?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                list.add(new Account(resultSet.getString(1),
                        resultSet.getBigDecimal(2),
                        resultSet.getInt(3),
                        resultSet.getBoolean(4)
                ));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
