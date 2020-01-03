package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.beans.Admin;
import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.ConnectionManager;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.SQLException;

public class InsertSql {
    public static void main(String[] args) throws SQLException {
        try (ConnectionManager connMgr = ConnectionManager.getInstance()) {
            AdminManager manager = new AdminManager(connMgr.getConnection());
            manager.displayAllRows();
            Admin bean = new Admin();
            bean.setUserName(InputHelper.getString("User name: "));
            bean.setPassword(InputHelper.getString("Password: "));
            boolean inserted = manager.insert(bean);
            if (inserted) {
                System.out.println("New row with primary key " + bean.getAdminId() + " was inserted.");
            } else {
                System.err.println("Failed to insert.");
            }
            manager.displayAllRows();
        }
    }
}
