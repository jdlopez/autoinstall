package io.github.autoinstall.domain.installs;

/**
 *
 * @author jdlopez
 */
public class AutoInstallResult {

    private boolean success;
    private String message;
    private String param;

    public AutoInstallResult() {}

    public AutoInstallResult(boolean ok, String message) {
        this.success = ok;
        this.message = message;
        this.param = null;
    }

    public AutoInstallResult(boolean ok, String message, String param) {
        this.success = ok;
        this.message = message;
        this.param = param;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
