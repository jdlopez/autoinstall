package io.github.autoinstall.domain.checks;

import io.github.autoinstall.MergePropertyUtils;
import io.github.autoinstall.domain.AutoInstallResult;

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
        File f = new File(MergePropertyUtils.mergeVars(path, System.getProperties()));
        if (!f.exists())
            return new AutoInstallResult(false, "check.path.not_found", f.toString());
        else if (writable != null && writable) {
            if (f.canWrite()) {
                return new AutoInstallResult(true, "check.path.writable.success");
            } else {
                return new AutoInstallResult(false, "check.path.not_writable", f.toString());
            }
        } else {
            return new AutoInstallResult(true, "check.path.success");
        }
    }

}
