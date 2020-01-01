package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.beans.Admin;
import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.SQLException;

public class UpdatableResultSet {
    public static void main(String[] args) throws SQLException {
        AdminManager.displayAllRows();
        int adminId = InputHelper.getInt("Select a row to update: ");
        Admin bean = AdminManager.getRow(adminId);
        if (bean == null) {
            System.err.println("Row not found");
            return;
        }
        System.out.println("Current values:");
        System.out.println(bean);
        String password = InputHelper.getString("Enter new password: ");
        bean.setPassword(password);
        boolean updated = AdminManager.updateRS(bean);
        if (updated) {
            System.out.println("Successfully Updated row:");
            System.out.println(AdminManager.getRow(bean.getAdminId()));
        } else {
            System.err.println("Failed to update");
        }
    }
}
