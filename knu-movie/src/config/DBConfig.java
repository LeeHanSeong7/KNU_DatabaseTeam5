package config;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class DBConfig {
    public final String DRIVER;
    public final String URL;
    public final String USERNAME;
    public final String PASSWORD;

    public DBConfig() {
        String resource = "db.properties";
        Properties properties = new Properties();

        try {
            InputStream is = getClass().getResourceAsStream(resource);
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.DRIVER = properties.getProperty("driver");
        this.URL = properties.getProperty("url");
        this.USERNAME = properties.getProperty("username");
        this.PASSWORD = properties.getProperty("password");
    }

    public DBConfig(String driver, String url, String username, String password) {
        this.DRIVER = driver;
        this.URL = url;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

}
