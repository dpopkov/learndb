package org.daydevjv.jdbcintegr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySql {
    public static void main(String[] args) {
        AppProperties app = new AppProperties("/connection.properties");
        try (Connection conn = DriverManager.getConnection(app.getUrl(), app.getUser(), app.getPassword())) {
            System.out.println("Connected: " + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
