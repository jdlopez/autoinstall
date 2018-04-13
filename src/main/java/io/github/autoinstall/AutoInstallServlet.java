package io.github.autoinstall;

import io.github.autoinstall.domain.installs.AutoInstallResult;
import io.github.autoinstall.domain.installs.InstallTask;
import io.github.autoinstall.domain.checks.PreCheckCondition;
import io.github.autoinstall.json.JsonMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author jdlopez
 */
public class AutoInstallServlet extends HttpServlet {

    private List<PreCheckCondition> preCheck;
    private List<InstallTask> installSteps;
    private String preCheckURI;
    private String installURI;
    private JsonMapper jsonMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        preCheck = readPreCheck(config);
        installSteps = readInstall(config);
        if (config.getInitParameter("autoinstall.preCheckURI") == null)
            preCheckURI = "check";
        else
            preCheckURI = config.getInitParameter("autoinstall.preCheckURI");
        if (config.getInitParameter("autoinstall.installURI") == null)
            installURI = "install";
        else
            installURI = config.getInitParameter("autoinstall.installURI");
        // mapper
        String jsonProvider = config.getInitParameter("autoinstall.jsonProvider");
        if (jsonProvider == null)
            jsonProvider = "gson";
        jsonMapper = JsonMapper.getInstance();
    }

    private List<InstallTask> readInstall(ServletConfig config) {
        return null;
    }

    private List<PreCheckCondition> readPreCheck(ServletConfig config) {
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        List<AutoInstallResult> messages;
        if (isInstalled(req)) {
            messages = Arrays.asList(new AutoInstallResult(false, getMessage("error.already_installed", req.getLocale())));
        } else {
            if (req.getRequestURI().equals(preCheckURI)) {
                messages = executePreCheck(preCheck, req);
            } else if (req.getRequestURI().equals(installURI)) {
                messages = executeInstall(installSteps, req);
            } else {
                messages = Arrays.asList(new AutoInstallResult(false, "error.action_notfound"));
            }
        }
        writeSimpleJson(messages, req.getLocale(), resp);
    }

    private boolean isInstalled(HttpServletRequest req) {
        return false;
    }

    private List<AutoInstallResult> executeInstall(List<InstallTask> installSteps, HttpServletRequest req) {
        List<AutoInstallResult> messages = new ArrayList<>();
        for (InstallTask install: installSteps) {
            messages.add(install.execute(req));
        }
        return messages;
    }

    private List<AutoInstallResult> executePreCheck(List<PreCheckCondition> preCheck, HttpServletRequest req) {
        List<AutoInstallResult> messages = new ArrayList<>();
        for (PreCheckCondition pre: preCheck) {
            messages.add(pre.validate(req));
        }
        return messages;
    }

    private void writeSimpleJson(List<AutoInstallResult> messages, Locale locale, HttpServletResponse resp) throws IOException {
        // get message keys
        //resp.getWriter().print(jsonMapper.serializeObject(messages));
    }

    private String getMessage(String key, Locale locale) {
        // cache with locale?
        ResourceBundle rb = ResourceBundle.getBundle("autoinstall", locale);
        return rb.getString(key);
    }


}
