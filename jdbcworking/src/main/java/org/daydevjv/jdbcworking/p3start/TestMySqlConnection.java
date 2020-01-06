package org.daydevjv.jdbcworking.p3start;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestMySqlConnection {

    public static final String PROPS_NAME = "/connection.mysql.properties";

    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        try (InputStream in = TestMySqlConnection.class.getResourceAsStream(PROPS_NAME)) {
            props.load(in);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load from resource: " + PROPS_NAME, e);
        }
        Connection conn = DriverManager.getConnection(props.getProperty("url"), props);
        System.out.println(conn);
        System.out.println("Connection to MySQL established successfully.");
    }
}
