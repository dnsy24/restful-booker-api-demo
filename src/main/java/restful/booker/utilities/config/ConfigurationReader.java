package restful.booker.utilities.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ConfigurationReader {
    private final Properties properties;

    private ConfigurationReader() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("configuration.properties"));
//            properties.load(this.getClass().getClassLoader().getResourceAsStream("/configuration.properties"));
        } catch (IOException e) {
            System.err.println("File upload failed!!!");
        }
    }

    private static class Holder {
        private static final ConfigurationReader instance = create();
    }

    private static ConfigurationReader create() {
        return new ConfigurationReader();
    }

    public static ConfigurationReader getInstance() {
        return Holder.instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}


