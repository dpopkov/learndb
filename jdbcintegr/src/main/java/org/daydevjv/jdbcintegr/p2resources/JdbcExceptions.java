package org.daydevjv.jdbcintegr.p2resources;

import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExceptions {
    public static void main(String[] args) {
        try (Connection conn = DbUtil.getConnection(DbType.HSQLDB)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            final String nonExistingTable = "state";
            ResultSet rs = stmt.executeQuery("SELECT stateId, stateName FROM " + nonExistingTable);
            rs.last();
            System.out.println("Number of rows: " + rs.getRow());
        } catch (SQLException e) {
            System.err.println("---------------------------");
            DbUtil.processException(e);
            System.err.println("---------------------------");
            e.printStackTrace();
        }
    }
}
