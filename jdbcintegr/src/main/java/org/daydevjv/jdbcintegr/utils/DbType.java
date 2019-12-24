package org.daydevjv.jdbcintegr.utils;

public enum DbType {
    HSQLDB("hsqldb"),
    MYSQL("mysql");

    private final String propertiesName;

    DbType(String rdbmsName) {
        this.propertiesName = "/connection." + rdbmsName + ".properties";
    }

    public String getPropertiesName() {
        return propertiesName;
    }
}
