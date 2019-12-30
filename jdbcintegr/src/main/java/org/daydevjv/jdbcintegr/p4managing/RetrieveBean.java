package org.daydevjv.jdbcintegr.p4managing;

import org.daydevjv.jdbcintegr.beans.Admin;
import org.daydevjv.jdbcintegr.tables.AdminManager;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.SQLException;

public class RetrieveBean {
    public static void main(String[] args) throws SQLException {
        AdminManager.displayAllRows();
        int adminId = InputHelper.getInt("Enter admin id: ");
        Admin bean = AdminManager.getRow(adminId);
        if (bean != null) {
            System.out.println(bean);
        } else {
            System.err.println("No rows were found");
        }
    }
}
