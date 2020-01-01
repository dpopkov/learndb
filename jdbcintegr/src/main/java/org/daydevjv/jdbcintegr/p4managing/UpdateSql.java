package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.beans.Admin;
import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.ConnectionManager;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.SQLException;

public class UpdateSql {
    public static void main(String[] args) throws SQLException {
        try {
            AdminManager manager = new AdminManager(ConnectionManager.getInstance().getConnection());
            manager.displayAllRows();
            int adminId = InputHelper.getInt("Select a row to update: ");
            Admin bean = manager.getRow(adminId);
            if (bean == null) {
                System.err.println("Row not found");
                return;
            }
            System.out.println("Current values:");
            System.out.println(bean);
            String password = InputHelper.getString("Enter new password: ");
            bean.setPassword(password);
            boolean updated = manager.update(bean);
            if (updated) {
                System.out.println("Successfully Updated row:");
                System.out.println(manager.getRow(bean.getAdminId()));
            } else {
                System.err.println("Failed to update");
            }
        } finally {
            ConnectionManager.getInstance().close();
        }
    }
}
