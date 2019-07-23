package com.epam.petersburg.ncr41.dao.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

    public Connection getConnection() throws SQLException;

}
