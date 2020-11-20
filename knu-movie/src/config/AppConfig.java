package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AppConfig {
    public final String REGION;
    public AppConfig() {
        String resource = "app.properties";
        Properties properties = new Properties();

        try {
            InputStream is = getClass().getResourceAsStream(resource);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.REGION = properties.getProperty("region");
    }
}
