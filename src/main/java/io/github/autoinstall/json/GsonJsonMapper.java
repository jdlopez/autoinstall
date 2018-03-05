package io.github.autoinstall.json;

public class GsonJsonMapper extends JsonMapper {
    @Override
    public <T> T deserializeObject(String json, Class<T> clazz) {
        return null;
    }

    @Override
    public String serializeObject(Object obj) {
        return null;
    }
}
