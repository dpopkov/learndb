package org.daydevjv.jdbcintegr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiConnect {
    public static void main(String[] args) {
        try (Connection conn = DbUtil.getConnection(DbType.HSQLDB)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM states");
            rs.last();
            System.out.println("Number of rows: " + rs.getRow());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
