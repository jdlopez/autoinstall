package io.github.autoinstall.json;

/**
 * Facade to hide implementation of json mapper (jackson or gson)
 * @author jdlopez
 */
public abstract class JsonMapper {

    private static final String JSON_PROVIDER_JACKSON = "jackson";
    private static final String JSON_PROVIDER_GSON = "gson";

    public static JsonMapper getInstance(String provider) {
        if (JSON_PROVIDER_JACKSON.equalsIgnoreCase(provider))
            return new JacksonJsonMapper();
        else if (JSON_PROVIDER_GSON.equalsIgnoreCase(provider))
            return new GsonJsonMapper();
        else
            throw new IllegalArgumentException("Provider not implemented: " + provider);
    }

    public abstract <T> T deserializeObject(String json, Class<T> clazz);

    public abstract String serializeObject(Object obj);
}
