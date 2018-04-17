package io.github.autoinstall;

import io.github.autoinstall.domain.AutoInstallResult;
import io.github.autoinstall.domain.checks.PreCheckCondition;
import io.github.autoinstall.json.JsonMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class ChecksTest {

    protected String getSampleJson() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/preconditions.json"))).lines()
                .parallel().collect(Collectors.joining("\n"));
    }

    @Test
    public void testPreconditions() throws IOException {
        String s = getSampleJson();
        JsonMapper mapper = JsonMapper.getInstance();
        List<PreCheckCondition> out = mapper.deserializePreChecks(s);
        StringBuilder sb = new StringBuilder();
        MockHttpServletRequest req = new MockHttpServletRequest();
        for (PreCheckCondition c: out) {
            AutoInstallResult response = c.validate(req);
            sb.append(c.getType()).append(": ").append(response.isSuccess()).append(" ").append(response.getMessage()).append("\n");
        }
        System.out.println("pre: " + sb.toString());

    }
}
