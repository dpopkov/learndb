package org.daydevjv.jdbcintegr.tables;

import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminManager {

    public static void displayAllRows() throws SQLException {
        String sql = "SELECT adminId, userName, password FROM admin";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Admin Table:");
            while (rs.next()) {
                System.out.println(rs.getInt("adminId") + ": "
                        + rs.getString("userName") + ", " + rs.getString("password"));
            }
        }
    }
}
