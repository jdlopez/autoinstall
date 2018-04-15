package io.github.autoinstall.domain.checks;

import io.github.autoinstall.domain.AutoInstallResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Check presence of a class in classpath. Useful for sql Driver or runtime dependencies and so
 * @author jdlopez
 */
public class CheckClass extends PreCheckCondition {
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public AutoInstallResult validate(HttpServletRequest req) {
        Class clazz = null;
        try {
            clazz = Class.forName(this.className);
            return new AutoInstallResult(true, "check.class.success");
        } catch (NullPointerException e) {
            return new AutoInstallResult(false, "check.class.wrong_param", e.getMessage());
        } catch (ClassNotFoundException e) {
            return new AutoInstallResult(false, "check.class.not_found", e.getMessage());
        }
    }
}
