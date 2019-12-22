package org.daydevjv.jdbcintegr.p3reading;

import org.daydevjv.jdbcintegr.tables.Tours;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Looping {
    public static void main(String[] args) {
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM tours");
            Tours.displayData(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
