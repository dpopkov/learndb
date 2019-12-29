package org.daydevjv.jdbcintegr.p3reading;

import org.daydevjv.jdbcintegr.tables.StatesManager;
import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scrollable {
    public static void main(String[] args) {
        try (Connection conn = DbUtil.getConnection(DbType.HSQLDB)) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT stateId, stateName FROM states");
            StatesManager.displayData(rs);
            rs.last();
            System.out.println("Number of rows: " + rs.getRow());
            rs.first();
            System.out.println("The first state is " + rs.getString("stateName"));
            rs.last();
            System.out.println("The last state is " + rs.getString("stateName"));
            rs.absolute(10);
            System.out.println("The 10th state is " + rs.getString("stateName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
