package com.epam.petersburg.ncr41.dao.impl;

import com.epam.petersburg.ncr41.dao.interfaces.UserDao;
import com.epam.petersburg.ncr41.dao.dbconnection.MyDataSource;
import com.epam.petersburg.ncr41.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public User findOne(Integer primaryKey) {
        User user = new User();
        try (Connection connection = MyDataSource.INSTANCE.getConnection()) {
            String sqlquery = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setInt(1, primaryKey);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
            }
            rs.close();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> listOfUsers = new ArrayList<>();
        try (Connection connection = MyDataSource.INSTANCE.getConnection()) {
            String sqlquery = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlquery);
            while (rs.next()) {
                User user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
                listOfUsers.add(user);
            }
            rs.close();
            statement.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return listOfUsers;
    }

    @Override
    public Optional<User> create(User user) {
        try (Connection connection = MyDataSource.INSTANCE.getConnection()) {
            String sqlquery = "INSERT INTO users (first_name, last_name, email, \"password\", role_id) VALUES(?, ?, ?, ?, ?) RETURNING user_id";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRole());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserId((rs.getInt(1)));
            }
            rs.close();
            preparedStatement.close();


        } catch (PSQLException e) {
            System.out.println("This user already exists");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return Optional.ofNullable(user);


    }

    @Override
    public void update(User user) {
        try (Connection connection = MyDataSource.INSTANCE.getConnection()) {
            String sqlquery = "UPDATE users SET first_name=?, last_name=?, email=?, \"password\"=?, role_id=? WHERE user_id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRole());
            preparedStatement.setInt(6, user.getUserId());
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }


    }

    @Override
    public void delete(User user) {
        try (Connection connection = MyDataSource.INSTANCE.getConnection()) {
            String sqlquery = "DELETE FROM users WHERE user_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = MyDataSource.INSTANCE.getConnection()) {
            String sqlquery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlquery);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6));
            }
            rs.close();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return user;
    }
}

