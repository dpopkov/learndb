package org.daydevjv.jdbcintegr.p3reading;

import org.daydevjv.jdbcintegr.tables.ToursManager;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LimitRows {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DbUtil.getConnection(DbType.HSQLDB)) {
            Statement stmt = conn.createStatement();
            /* Do not use setMaxRows method. */
            /* stmt.setMaxRows(5); */
            ResultSet rs = stmt.executeQuery("SELECT tourId, tourName, price FROM tours LIMIT 10, 5");
            ToursManager.displayData(rs);
        }
    }
}
