package io.github.autoinstall.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.autoinstall.domain.checks.PreCheckCondition;
import io.github.autoinstall.exception.ConfigurationException;

import java.util.LinkedList;
import java.util.List;

public class GsonJsonMapper extends JsonMapper {

    @Override
    public List<PreCheckCondition> deserializePreChecks(String json) {
        JsonParser jsonParser = new JsonParser();
        Gson gson = new Gson();
        LinkedList<PreCheckCondition> ret = new LinkedList<>();
        String packageBase = PreCheckCondition.class.getPackageName();
        JsonElement tree = jsonParser.parse(json);
        if (tree.isJsonArray()) {
            for (JsonElement check: tree.getAsJsonArray()) {
                if (check.isJsonObject()) {
                    JsonElement typeNameObj = check.getAsJsonObject().get("type");
                    try {
                        Class clazz = Class.forName(packageBase + "." + typeNameObj.getAsString());
                        PreCheckCondition pre = (PreCheckCondition)gson.fromJson(check, clazz);
                        ret.add(pre);
                    } catch (ClassNotFoundException e) {
                        throw new ConfigurationException("PreCheckClass not found: " + e.getMessage());
                    }
                } else {
                    throw new ConfigurationException("Element " + check +" must be an object");
                }
            }
        } else {
            throw new ConfigurationException("JSON is not an array");
        }
        return ret;
    }

    @Override
    public String serializePreChecks(List<PreCheckCondition> objs) {
        return null;
    }
}
