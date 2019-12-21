package org.daydevjv.jdbcintegr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Example of connection to hsqldb.
 *
 * Uses resource file containing key-value pairs:
 * user=hsqluser
 * password=hsqlpassword
 * url=jdbc:hsqldb:data/explorecalifornia
 */
public class ConnectHsqlDb {
    public static void main(String[] args) {
        ConnectionProperties app = new ConnectionProperties("/connection.hsqldb.properties");
        try (Connection conn = DriverManager.getConnection(app.getUrl(), app.getUser(), app.getPassword())) {
            System.out.println("Connected: " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
