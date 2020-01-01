package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.SQLException;

public class DeleteSql {
    public static void main(String[] args) throws SQLException {
        AdminManager.displayAllRows();
        int adminId = InputHelper.getInt("Enter row to delete: ");
        if (AdminManager.delete(adminId)) {
            System.out.println("Successfully deleted.");
        } else {
            System.err.println("Nothing to delete.");
        }
        AdminManager.displayAllRows();
    }
}
