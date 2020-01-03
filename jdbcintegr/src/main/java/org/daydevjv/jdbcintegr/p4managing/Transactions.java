package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.beans.Admin;
import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.ConnectionManager;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.Connection;
import java.sql.SQLException;

public class Transactions {
    public static void main(String[] args) throws SQLException {
        try (ConnectionManager connMgr = ConnectionManager.getInstance()) {
            AdminManager adminTable = new AdminManager(connMgr.getConnection());
            adminTable.displayAllRows();
            int adminId = InputHelper.getInt("Select a row to update: ");
            Admin bean = adminTable.getRow(adminId);
            if (bean == null) {
                System.err.println("Row not found");
                return;
            }
            System.out.println("Current values:");
            System.out.println(bean);
            String password = InputHelper.getString("Enter new password: ");
            bean.setPassword(password);

            Connection conn = connMgr.getConnection();
            conn.setAutoCommit(false);
            boolean updated = adminTable.update(bean);
            if (updated) {
                System.out.println("Successfully Updated row:");
                System.out.println(adminTable.getRow(bean.getAdminId()));
            } else {
                System.err.println("Failed to update");
            }
            String answer = InputHelper.getString("Commit(c) or Rollback(r): ");
            if ("c".equals(answer)) {
                conn.commit();
                System.out.println("Transaction committed");
            } else {
                conn.rollback();
                System.out.println("Transaction rolled back");
            }
        }
    }
}
