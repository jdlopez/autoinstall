package io.github.autoinstall.domain.checks;

import io.github.autoinstall.domain.installs.AutoInstallResult;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class CheckPath extends PreCheckCondition {

    private String path;
    private Boolean writable;

    public CheckPath() {
        type = "ValidatePath";
    }

    @Override
    public AutoInstallResult validate(HttpServletRequest req) {
        File f = new File(path);
        if (f.exists())
            return new AutoInstallResult(true, "check.path.success");
        else
            return new AutoInstallResult(false, "check.path.not_found");

        // add writable validation
    }

}
