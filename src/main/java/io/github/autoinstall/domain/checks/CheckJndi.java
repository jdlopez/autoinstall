package io.github.autoinstall.domain.checks;

import io.github.autoinstall.domain.installs.AutoInstallResult;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class CheckJndi extends PreCheckCondition {
    private String jndi;
    private String className;

    public String getJndi() {
        return jndi;
    }

    public void setJndi(String jndi) {
        this.jndi = jndi;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public AutoInstallResult validate(HttpServletRequest req) {
        try {
            InitialContext ic = new InitialContext();
            Object o = ic.lookup(jndi);
            if (o == null)
                return new AutoInstallResult(false, "check.jndi.not_found");
            else if (o.getClass().getName().equalsIgnoreCase(className))
                return new AutoInstallResult(true, "check.jndi.success");
            else
                return new AutoInstallResult(false, "check.jndi.wrong_class", o.getClass().getName());
        } catch (NamingException e) {
            return new AutoInstallResult(false, "check.jndi.exception", e.getMessage());
        }
    }

}
