package org.daydevjv.jdbcintegr.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

public class Tours {
    public static void displayData(ResultSet rs) throws SQLException {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
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
