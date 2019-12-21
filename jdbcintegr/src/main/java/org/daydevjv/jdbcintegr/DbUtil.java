package org.daydevjv.jdbcintegr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static final String MYSQL_RESOURCE_NAME = "/connection.mysql.properties";
    public static final String HSQLDB_RESOURCE_NAME = "/connection.hsqldb.properties";

    public static Connection getConnection(DbType dbType) throws SQLException {
        ConnectionProperties app;
        switch (dbType) {
            case MYSQL:
                app = new ConnectionProperties(MYSQL_RESOURCE_NAME);
                break;
            case HSQLDB:
                app = new ConnectionProperties(HSQLDB_RESOURCE_NAME);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + dbType);
        }
        return DriverManager.getConnection(app.getUrl(), app.getUser(), app.getPassword());
    }
}
