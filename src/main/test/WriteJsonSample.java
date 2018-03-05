import com.google.gson.Gson;
import io.github.autoinstall.domain.CheckJndi;
import io.github.autoinstall.domain.CheckPath;
import io.github.autoinstall.domain.PreCheckCondition;

import java.util.ArrayList;
import java.util.List;

public class WriteJsonSample {

    public static void main(String[] args) {
        Gson gson = new Gson();
        List<PreCheckCondition> arr = new ArrayList<>();
        CheckJndi jndi = new CheckJndi();
        jndi.setClassName("sample");
        jndi.setJndi("jndi_path");
        arr.add(jndi);
        CheckPath path = new CheckPath();
        path.setPath("/home");
        arr.add(path);
        String sout = gson.toJson(arr);
        System.out.println(sout);

        PreCheckCondition[] undo = gson.fromJson(sout, PreCheckCondition[].class);
        System.out.println(gson.toJson(undo));
    }
}
