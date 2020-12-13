package restful.booker.utilities.owner.config;

import org.aeonbits.owner.Config;

//@Sources({"classpath:env/${env}.properties"})
@Config.Sources({"classpath:env/api-qa1.properties"})
public interface Configurator extends Config {

    @Key("uri")
    String uriName();

}


