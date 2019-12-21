package org.daydevjv.jdbcintegr.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConnection(DbType dbType) throws SQLException {
        ConnectionProperties app = new ConnectionProperties(dbType.getPropertiesName());
        return DriverManager.getConnection(app.getUrl(), app.getUser(), app.getPassword());
    }

    /** Outputs detailed information about SQLException. */
    public static void processException(SQLException e) {
        System.err.println("Error message: " + e.getMessage());
        System.err.println("Error code: " + e.getErrorCode());
        System.err.println("SQL state: " + e.getSQLState());
    }
}
