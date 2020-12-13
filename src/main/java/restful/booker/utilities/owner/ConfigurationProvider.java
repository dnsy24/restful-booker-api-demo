package restful.booker.utilities.owner;

import org.aeonbits.owner.ConfigFactory;
import restful.booker.utilities.owner.config.Configurator;

public class ConfigurationProvider {
    private static ConfigurationProvider instance;

    private Configurator configuration;

    private ConfigurationProvider() {
        configuration = ConfigFactory.newInstance().create(Configurator.class);
    }

    public Configurator getConfiguration() {
        return configuration;
    }

    public static ConfigurationProvider getInstance() {
        if (instance == null) {
            instance = new ConfigurationProvider();
        }
        return instance;
    }
}
