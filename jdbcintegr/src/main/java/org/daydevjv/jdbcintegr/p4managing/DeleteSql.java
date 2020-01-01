package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.ConnectionManager;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.SQLException;

public class DeleteSql {
    public static void main(String[] args) throws SQLException {
        try {
            if (ConnectionManager.getInstance().getDbType() != DbType.MYSQL) {
                ConnectionManager.getInstance().setDbType(DbType.MYSQL);
            }
            AdminManager manager = new AdminManager(ConnectionManager.getInstance().getConnection());
            manager.displayAllRows();
            int adminId = InputHelper.getInt("Enter row to delete: ");
            if (manager.delete(adminId)) {
                System.out.println("Successfully deleted.");
            } else {
                System.err.println("Nothing to delete.");
            }
            manager.displayAllRows();
        } finally {
            ConnectionManager.getInstance().close();
        }
    }
}
