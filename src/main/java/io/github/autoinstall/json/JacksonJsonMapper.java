package io.github.autoinstall.json;

import io.github.autoinstall.domain.checks.PreCheckCondition;

import java.util.List;

public class JacksonJsonMapper extends JsonMapper {

    @Override
    public List<PreCheckCondition> deserializePreChecks(String json) {
        return null;
    }

    @Override
    public String serializePreChecks(List<PreCheckCondition> objs) {
        return null;
    }
}
