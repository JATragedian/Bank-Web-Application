package com.epam.petersburg.ncr41.dao.impl;

import com.epam.petersburg.ncr41.dao.interfaces.RoleDao;
import com.epam.petersburg.ncr41.dao.dbconnection.DataSource;
import com.epam.petersburg.ncr41.dao.dbconnection.MyDataSource;
import com.epam.petersburg.ncr41.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao {

    private static final String SELECT_BY_ID = "SELECT * FROM roles WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM roles";
    private static final String CREATE = "INSERT INTO roles (role) VALUES (?)";
    private static final String UPDATE = "UPDATE roles SET role = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM roles WHERE id=?";
    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);

    private DataSource dataSource = MyDataSource.INSTANCE;

    @Override
    public Role findOne(Integer id) {
        Role role = new Role();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                role.setRoleId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));
                roles.add(role);
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return roles;
    }

    @Override
    public Optional<Role> create(Role role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, role.getRole());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                role.setRoleId(resultSet.getInt("id"));
            }
            resultSet.close();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Optional.ofNullable(role);
    }

    @Override
    public void update(Role role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, role.getRole());
            preparedStatement.setInt(2, role.getRoleId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Role role) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, role.getRoleId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
