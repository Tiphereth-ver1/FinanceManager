package financemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties props;
    private final String configPath;

    public ConfigLoader(String filePath) {
        this.props = new Properties();
        File file = new File(filePath);
        this.configPath = filePath;

        try {
            if (file.exists()) {
                // Load from external config file
                try (InputStream in = new FileInputStream(file)) {
                    props.load(in);
                }
            } else {
                // Load default from classpath
                try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                    if (in == null) {
                        throw new FileNotFoundException("Default config.properties not found in resources.");
                    }
                    props.load(in);
                }

                // Save a copy to external config location
                file.getParentFile().mkdirs(); // Create directories if missing
                try (OutputStream out = new FileOutputStream(file)) {
                    props.store(out, "Initial external config");
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log or rethrow as needed
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }

    public String getOrDefault(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    public void set(String key, String value) {
        props.setProperty(key,value);
    }

    public void save(String filePath) {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            props.store(out, "Updated config");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
    save(this.configPath);}



    }
    
