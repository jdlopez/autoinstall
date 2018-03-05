package io.github.autoinstall.domain;

import javax.servlet.http.HttpServletRequest;

public abstract class PreCheckCondition {

    public abstract AutoInstallResult validate(HttpServletRequest req);
}
