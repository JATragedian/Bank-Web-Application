package com.epam.petersburg.ncr41.dao.dbconnection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public enum MyDataSource implements DataSource {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(MyDataSource.class);

    @Override
    public Connection getConnection() throws SQLException {

        Properties props = new Properties();
        File file = new File(
                getClass().getClassLoader().getResource("JDBC.properties").getFile()
        );

        try {
            props.load(new FileInputStream(file));
//            props.load(new FileInputStream("C:\\Users\\JATragedian\\IdeaProjects\\student-project-1907\\src\\main\\resources\\JDBC.properties"));
            Class.forName(props.getProperty("DB_DRIVER_CLASS"));
        } catch (ClassNotFoundException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        Connection connection = DriverManager.getConnection(
                props.getProperty("DB_URL"), props.getProperty("DB_USERNAME"), props.getProperty("DB_PASSWORD"));
        System.out.println("Connection is successful. \nDataBase name: " + connection.getCatalog() );
        return connection;
    }


    @Override
    public String toString() {
        return this.getClass().getName();
    }


}
