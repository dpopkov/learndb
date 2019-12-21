package org.daydevjv.jdbcintegr.utils;

public enum DbType {
    HSQLDB("hsqldb"),
    MYSQL("mysql");

    private final String propertiesName;

    DbType(String dbName) {
        this.propertiesName = "/connection." + dbName + ".properties";
    }

    public String getPropertiesName() {
        return propertiesName;
    }
}
