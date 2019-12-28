package org.daydevjv.jdbcintegr.p3reading;

import org.daydevjv.jdbcintegr.tables.Tours;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.*;

public class StoredProc {
    private static final String SQL = "{call GetToursByPrice(?)}";

    public static void main(String[] args) {
        double maxPrice = InputHelper.getDouble("Enter a maximum price: ");
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            CallableStatement stmt = conn.prepareCall(SQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setDouble(1, maxPrice);
            ResultSet rs = stmt.executeQuery();
            Tours.displayData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
