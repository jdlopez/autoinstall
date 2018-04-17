package io.github.autoinstall.domain.installs;

import io.github.autoinstall.domain.AutoInstallResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class InstallTask {

    private String name;
    private String type;
    private List<VarInfo> vars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<VarInfo> getVars() {
        return vars;
    }

    public void setVars(List<VarInfo> vars) {
        this.vars = vars;
    }

    public abstract AutoInstallResult execute(HttpServletRequest req);

}
