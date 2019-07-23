package com.epam.petersburg.ncr41.dao.interfaces;

import com.epam.petersburg.ncr41.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User, Integer> {
    @Override
    User findOne(Integer primaryKey);

    @Override
    List<User> findAll();

    @Override
    Optional<User> create(User user);

    @Override
    void update(User user);

    @Override
    void delete(User user);

    User findByEmail(String email);
}
