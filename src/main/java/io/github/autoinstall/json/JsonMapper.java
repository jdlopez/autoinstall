package io.github.autoinstall.json;

import io.github.autoinstall.domain.checks.PreCheckCondition;
import io.github.autoinstall.exception.ConfigurationException;

import java.util.List;

/**
 * Facade to hide implementation of json mapper (jackson or gson)
 * @author jdlopez
 */
public abstract class JsonMapper {

    public static JsonMapper getInstance() {
        try { // 1st
            Class gsonClass = Class.forName("com.google.gson.Gson");
            return new GsonJsonMapper();
        } catch (ClassNotFoundException e) {
            try {
                Class jacksonClass = Class.forName("com.google.gson.Gson");
                return new JacksonJsonMapper();
            } catch (ClassNotFoundException e1) { // none!!
                throw new ConfigurationException("No JSON provider found on classpath: Gson or Jackson");
            }
        }
    }

    public abstract List<PreCheckCondition> deserializePreChecks(String json);

    public abstract String serializePreChecks(List<PreCheckCondition> objs);
}
