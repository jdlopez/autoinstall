package io.github.autoinstall.domain.checks;

import io.github.autoinstall.domain.AutoInstallResult;

import javax.servlet.http.HttpServletRequest;

public abstract class PreCheckCondition {
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract AutoInstallResult validate(HttpServletRequest req);
}
