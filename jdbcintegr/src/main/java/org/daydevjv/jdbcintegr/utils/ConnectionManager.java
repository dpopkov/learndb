package org.daydevjv.jdbcintegr.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance = null;

    private DbType dbType = DbType.MYSQL;
    private ConnectionProperties connProps = readProperties();

    private ConnectionProperties readProperties() {
        return new ConnectionProperties(dbType.getPropertiesName());
    }

    private Connection conn = null;

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
        connProps = readProperties();
    }

    public DbType getDbType() {
        return dbType;
    }

    private boolean openConnection() {
        try {
            conn = DriverManager.getConnection(connProps.getUrl(), connProps.getUser(), connProps.getPassword());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        if (conn == null) {
            if (openConnection()) {
                System.out.println("Connection opened");
                return conn;
            } else {
                System.err.println("Failed to open connection");
                return null;
            }
        }
        return conn;
    }

    public void close() {
        System.out.println("Closing connection");
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
