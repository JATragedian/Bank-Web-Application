package com.epam.petersburg.ncr41.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {

    T findOne(K primaryKey);

    List<T> findAll();

    Optional<T> create(T t);

    void update(T t);

    void delete(T t);
}
