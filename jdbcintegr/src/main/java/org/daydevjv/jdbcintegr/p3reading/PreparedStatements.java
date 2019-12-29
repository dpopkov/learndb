package org.daydevjv.jdbcintegr.p3reading;

import org.daydevjv.jdbcintegr.tables.ToursManager;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.*;

public class PreparedStatements {
    private static final String SQL = "SELECT tourId, tourName, price FROM tours WHERE price <= ?";

    public static void main(String[] args) {
        double maxPrice = InputHelper.getDouble("Enter a maximum price: ");
        try (Connection conn = DbUtil.getConnection(DbType.HSQLDB)) {
            PreparedStatement stmt = conn.prepareStatement(SQL,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setDouble(1, maxPrice);
            ResultSet rs = stmt.executeQuery();
            ToursManager.displayData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
