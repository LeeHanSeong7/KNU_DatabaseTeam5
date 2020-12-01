package knu.movie.app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    public final String REGION;
    public AppConfig() {
        String resource = "app.properties";
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

        this.REGION = properties.getProperty("region");
    }
}
