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
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            System.out.println("Number of tours: " + nRows);
            rs.beforeFirst();
            while (rs.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("Tour ").append(rs.getInt("tourId")).append(": ");
                builder.append(rs.getString("tourName"));
                double price = rs.getDouble("price");
                builder.append(" (").append(nf.format(price)).append(")");
                System.out.println(builder);
            }
        }
    }
}
