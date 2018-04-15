package io.github.autoinstall.domain.checks;

import io.github.autoinstall.domain.AutoInstallResult;

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
        Class clazz = null;
        Object o = null;
        try {
            clazz = Class.forName(this.className);
        } catch (ClassNotFoundException e) {
            return new AutoInstallResult(false, "check.jndi.class_not_found", e.getMessage());
        }
        try {
            InitialContext ic = new InitialContext();
            o = ic.lookup(jndi);
        } catch (NamingException e) {
            return new AutoInstallResult(false, "check.jndi.exception", e.getMessage());
        }
        if (o == null)
            return new AutoInstallResult(false, "check.jndi.name_not_found");
        else if (clazz.isInstance(o))
            return new AutoInstallResult(true, "check.jndi.success");
        else
            return new AutoInstallResult(false, "check.jndi.wrong_class", clazz.getName());
    }

}
