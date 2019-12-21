package org.daydevjv.jdbcintegr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Example of connection to MySQL.
 *
 * Uses resource file containing key-value pairs:
 * user=______
 * password=_______
 * url=jdbc:mysql://localhost/explorecalifornia?useSSL=false
 */
public class ConnectMySql {
    public static void main(String[] args) {
        AppProperties app = new AppProperties("/connection.mysql.properties");
        try (Connection conn = DriverManager.getConnection(app.getUrl(), app.getUser(), app.getPassword())) {
            System.out.println("Connected: " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
