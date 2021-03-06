package org.daydevjv.jdbcintegr.p1start;

import org.daydevjv.jdbcintegr.utils.ConnectionProperties;

import java.sql.*;

public class StaticSql {
    public static void main(String[] args) {
        ConnectionProperties app = new ConnectionProperties("/connection.hsqldb.properties");
        try (Connection conn = DriverManager.getConnection(app.getUrl(), app.getUser(), app.getPassword())) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM states");
            rs.last();
            System.out.println("Number of rows: " + rs.getRow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
