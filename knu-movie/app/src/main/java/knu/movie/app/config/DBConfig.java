package knu.movie.app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
    public final String DRIVER;
    public final String URL;
    public final String USERNAME;
    public final String PASSWORD;
    public Connection connection;

    public DBConfig() {
        String resource = "db.properties";
        Properties properties = new Properties();

        try {
            String externalFileName = System.getProperty(resource); 
            if (externalFileName != null) {
                InputStream fin = new FileInputStream(new File(externalFileName));        
                properties.load(fin);
            } else {
                InputStream is = getClass().getResourceAsStream(resource);
                properties.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        this.DRIVER = properties.getProperty("driver");
        this.URL = properties.getProperty("url");
        this.USERNAME = properties.getProperty("username");
        this.PASSWORD = properties.getProperty("password");
        setConnection();
    }

    public DBConfig(String driver, String url, String username, String password) {
        this.DRIVER = driver;
        this.URL = url;
        this.USERNAME = username;
        this.PASSWORD = password;
        setConnection();
    }

    private void setConnection() {
        // try to connect
        try {
            Class.forName(DRIVER);
        } catch(ClassNotFoundException e) {
            System.err.println("error = " + e.getMessage());
            System.exit(1);
        }
        
        try {
            connection = DriverManager.getConnection(URL,  USERNAME, PASSWORD);
            connection.setAutoCommit(false);
        }catch(SQLException ex) {
            System.out.println("err)Cannot get a connection : " + ex.getMessage());
            System.exit(1);
        }
    }

}
