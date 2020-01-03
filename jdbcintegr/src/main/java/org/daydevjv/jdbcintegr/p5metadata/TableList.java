package org.daydevjv.jdbcintegr.p5metadata;

import org.daydevjv.jdbcintegr.utils.ConnectionManager;
import org.daydevjv.jdbcintegr.utils.DbType;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableList {

    public static void main(String[] args) throws SQLException {
        DbType dbType = null;
        if (args.length > 0) {
            dbType = DbType.valueOf(args[0].toUpperCase());
        }
        if (dbType != null && ConnectionManager.getInstance().getDbType() != dbType) {
            ConnectionManager.getInstance().setDbType(dbType);
            System.out.println("Using dbType " + dbType);
        }
        try (Connection conn = ConnectionManager.getInstance().getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            final String[] tableTypes = {"TABLE"};
            ResultSet rsTables = metaData.getTables(null, "%", "%", tableTypes);
            // Parameter 'catalog' = null - it is name of the database and is already selected in the connection string
            // Parameters 'schemaPattern' and 'tableNamePattern' are set as wildcard '%'
            final String columnLabel = "TABLE_NAME";
            System.out.println("List of tables:");
            while (rsTables.next()) {
                String tableName = rsTables.getString(columnLabel);
                System.out.println(tableName);
            }
        }
    }
}
