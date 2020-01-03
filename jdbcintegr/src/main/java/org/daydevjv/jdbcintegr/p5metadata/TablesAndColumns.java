package org.daydevjv.jdbcintegr.p5metadata;

import org.daydevjv.jdbcintegr.utils.ConnectionManager;
import org.daydevjv.jdbcintegr.utils.DbType;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TablesAndColumns {
    public static void main(String[] args) throws SQLException {
        DbType dbType = null;
        if (args.length > 0) {
            dbType = DbType.valueOf(args[0].toUpperCase());
        }
        if (dbType != null && ConnectionManager.getInstance().getDbType() != dbType) {
            ConnectionManager.getInstance().setDbType(dbType);
            System.out.println("Using dbType " + dbType);
        }
        List<String> tables = new ArrayList<>();
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            final String[] tableTypes = {"TABLE"};
            ResultSet rsTables = metaData.getTables(null, "%", "%", tableTypes);
            final String columnLabel = "TABLE_NAME";
            while (rsTables .next()) {
                tables.add(rsTables.getString(columnLabel));
            }
            for (String tableName : tables) {
                System.out.println("Table: " + tableName);
                System.out.println("----------------");
                ResultSet rsColumns = metaData.getColumns(null, "%", tableName, "%");
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    String typeName = rsColumns.getString("TYPE_NAME");
                    System.out.printf("%s: %s%n", columnName, typeName);
                }
                System.out.println();
            }
        }
    }
}
