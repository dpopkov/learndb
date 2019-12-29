package org.daydevjv.jdbcintegr.p3reading;

import org.daydevjv.jdbcintegr.tables.ToursManager;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;
import org.daydevjv.jdbcintegr.utils.InputHelper;

import java.sql.*;

public class StoredProcOut {
    private static final String SQL = "{call GetToursWithCountByPrice(?, ?)}";

    public static void main(String[] args) {
        double maxPrice = InputHelper.getDouble("Enter a maximum price: ");
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            CallableStatement stmt = conn.prepareCall(SQL,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            stmt.setDouble(1, maxPrice);
            stmt.registerOutParameter("total", Types.INTEGER);
            ResultSet rs = stmt.executeQuery();
            int nRows = stmt.getInt("total");
            ToursManager.displayData(rs, nRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
