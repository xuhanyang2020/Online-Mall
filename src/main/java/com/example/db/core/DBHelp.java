package com.example.db.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//create connection between java program and mysql database
public class DBHelp {
    static String url = "jdbc:mysql://localhost:3306/store?serverTimezone=UTC";
    static String user = "root";
    static String password = "Asdf980709";
    static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DataAccessException("jdbctemplte exception class not found", e);
        }
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
