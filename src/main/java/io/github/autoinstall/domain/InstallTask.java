package io.github.autoinstall.domain;

import javax.servlet.http.HttpServletRequest;

public abstract class InstallTask {

    public abstract AutoInstallResult execute(HttpServletRequest req);

}
