package org.daydevjv.jdbcintegr.tables;

import org.daydevjv.jdbcintegr.beans.Admin;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.*;

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

    public static Admin getRow(int adminId) throws SQLException {
        String sql = "SELECT adminId, userName, password FROM admin WHERE adminId = ?";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(adminId);
                admin.setUserName(rs.getString("userName"));
                admin.setPassword(rs.getString("password"));
                return admin;
            }
        }
        return null;
    }
}
