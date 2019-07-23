package com.epam.petersburg.ncr41.dao.interfaces;

import com.epam.petersburg.ncr41.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends Dao<Role, Integer> {

    @Override
    Role findOne(Integer id);

    @Override
    List<Role> findAll();

    @Override
    Optional<Role> create(Role role);

    @Override
    void update(Role role);

    @Override
    void delete(Role role);
}
