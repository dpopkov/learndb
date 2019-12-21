package org.daydevjv.jdbcintegr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Represents properties of connection.<br>
 */
public class ConnectionProperties {
    private final Properties props = new Properties();

    public ConnectionProperties(String resourceFileName) {
        try (InputStream in = ConnectionProperties.class.getResourceAsStream(resourceFileName)) {
            props.load(in);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load from resource: " + resourceFileName, e);
        }
    }

    public String getUrl() {
        return props.getProperty("url");
    }

    public String getUser() {
        return props.getProperty("user");
    }

    public String getPassword() {
        return props.getProperty("password");
    }
}
