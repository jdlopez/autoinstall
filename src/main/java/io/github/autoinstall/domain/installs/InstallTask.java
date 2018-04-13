package io.github.autoinstall.domain.installs;

import javax.servlet.http.HttpServletRequest;

public abstract class InstallTask {

    public abstract AutoInstallResult execute(HttpServletRequest req);

}
