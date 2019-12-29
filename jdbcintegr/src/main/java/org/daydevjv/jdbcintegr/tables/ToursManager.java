package org.daydevjv.jdbcintegr.tables;

import org.daydevjv.jdbcintegr.utils.DbType;
import org.daydevjv.jdbcintegr.utils.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

public class ToursManager {

    public static void displayAllRows() throws SQLException {
        String sql = "SELECT tourId, packageId, tourName, blurb, description, price, difficulty, "
                + "graphic, length, region, keywords FROM tours";
        try (Connection conn = DbUtil.getConnection(DbType.MYSQL)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Tours Table:");
            while (rs.next()) {
                System.out.println(rs.getInt("tourId") + ": " + rs.getInt("packageId")
                        + ", " + rs.getString("tourName") + ", " + rs.getString("blurb")
                        + ", " + rs.getString("description") + ", " + rs.getDouble("price")
                        + ", " + rs.getString("difficulty") + ", " + rs.getString("graphic")
                        + ", " + rs.getInt("length") + ", " + rs.getString("region")
                        + ", " + rs.getString("keywords"));
            }
        }
    }

    /* Methods from previous parts */

    public static void displayData(ResultSet rs) throws SQLException {
        rs.last();
        int nRows = rs.getRow();
        if (nRows == 0) {
            System.out.println("No tours were found");
        } else {
            rs.beforeFirst();
            displayData(rs, nRows);
        }
    }

    public static void displayData(ResultSet rs, int nRows) throws SQLException {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println("Number of tours: " + nRows);
        while (rs.next()) {
            int tourId      = rs.getObject("tourId", Integer.class);
            String tourName = rs.getObject("tourName", String.class);
            double price    = rs.getObject("price", Double.class);
            System.out.println("Tour " + tourId + ": " + tourName + " (" + nf.format(price) + ")");
        }
    }
}
