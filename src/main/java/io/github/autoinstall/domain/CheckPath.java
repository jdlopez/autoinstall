package io.github.autoinstall.domain;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class CheckPath extends PreCheckCondition {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public AutoInstallResult validate(HttpServletRequest req) {
        File f = new File(path);
        if (f.exists())
            return new AutoInstallResult(true, "check.path.success");
        else
            return new AutoInstallResult(false, "check.path.not_found");
    }
}
