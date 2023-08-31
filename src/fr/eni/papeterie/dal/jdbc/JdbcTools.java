package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            Settings.getProperties("url"),
            Settings.getProperties("user"),
            Settings.getProperties("password")
        );
    }
}

