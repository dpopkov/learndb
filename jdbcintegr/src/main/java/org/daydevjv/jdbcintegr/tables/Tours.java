package org.daydevjv.jdbcintegr.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {
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
