package org.daydevjv.jdbcintegr.tables;

import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatesManager {

    public static void displayAllRows() throws SQLException {
        String sql = "SELECT stateId, stateName FROM states";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("States Table:");
            while (rs.next()) {
                System.out.println(rs.getInt("stateId") + ": " + rs.getString("stateName"));
            }
        }
    }

    /* Method from previous parts */

    public static void displayData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            String stateFullName = rs.getString("stateId") + ": " + rs.getString("stateName");
            System.out.println(stateFullName);
        }
    }
}
