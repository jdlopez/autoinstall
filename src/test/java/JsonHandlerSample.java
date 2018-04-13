import io.github.autoinstall.domain.checks.PreCheckCondition;
import io.github.autoinstall.json.GsonJsonMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class JsonHandlerSample {

    protected String getSampleJson() throws IOException {
        //return
        return new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/preconditions.json"))).lines()
                .parallel().collect(Collectors.joining("\n"));
    }

    @Test
    public void testGsonRead() throws IOException {

        String s = getSampleJson();
        GsonJsonMapper gson = new GsonJsonMapper();
        List<PreCheckCondition> out = gson.deserializePreChecks(s);
        StringBuilder sb = new StringBuilder();
        for (PreCheckCondition c: out)
            sb.append(c.getType()).append("\n");
        System.out.println("pre: " + sb.toString());

    }
}
