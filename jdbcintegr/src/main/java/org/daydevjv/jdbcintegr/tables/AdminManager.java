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
                Admin bean = new Admin();
                bean.setAdminId(adminId);
                bean.setUserName(rs.getString("userName"));
                bean.setPassword(rs.getString("password"));
                return bean;
            }
        }
        return null;
    }

    public static boolean insert(Admin bean) throws SQLException {
        String sql = "INSERT into admin (userName, password) VALUES (?, ?)";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    int newKey = keys.getInt(1);
                    bean.setAdminId(newKey);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean update(Admin bean) throws SQLException {
        String sql = "UPDATE admin SET userName = ?, password = ? WHERE adminId = ?";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            stmt.setInt(3, bean.getAdminId());
            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean updateRS(Admin bean) throws SQLException {
        String sql = "SELECT * FROM admin WHERE adminId = ?";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            PreparedStatement stmt = conn.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, bean.getAdminId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                rs.updateString("userName", bean.getUserName());
                rs.updateString("password", bean.getPassword());
                rs.updateRow();
                return true;
            }
        }
        return false;
    }

    public static boolean delete(int adminId) throws SQLException {
        String sql = "DELETE FROM admin WHERE adminId = ?";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, adminId);
            int deleted = stmt.executeUpdate();
            if (deleted == 1) {
                return true;
            }
        }
        return false;
    }
}
