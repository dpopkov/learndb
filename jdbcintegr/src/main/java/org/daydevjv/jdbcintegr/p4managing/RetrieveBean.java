package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.beans.Admin;
import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.ConnectionManager;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.SQLException;

public class RetrieveBean {
    public static void main(String[] args) throws SQLException {
        try {
            ConnectionManager.getInstance().setDbType(DbType.HSQLDB);
            AdminManager manager = new AdminManager(ConnectionManager.getInstance().getConnection());
            manager.displayAllRows();
            int adminId = InputHelper.getInt("Enter admin id: ");
            Admin bean = manager.getRow(adminId);
            if (bean != null) {
                System.out.println(bean);
            } else {
                System.err.println("No rows were found");
            }
        } finally {
            ConnectionManager.getInstance().close();
        }
    }
}
