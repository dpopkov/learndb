package org.daydevjv.jdbcintegr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Represents properties of application.<br>
 *
 * Uses resource file containing key-value pairs:
 * user=______
 * password=_______
 * url=jdbc:mysql://localhost/explorecalifornia?useSSL=false
 */
public class AppProperties {
    private final Properties props = new Properties();

    public AppProperties(String resourceFileName) {
        try (InputStream in = AppProperties.class.getResourceAsStream(resourceFileName)) {
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
