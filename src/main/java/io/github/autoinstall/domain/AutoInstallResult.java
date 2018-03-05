package io.github.autoinstall.domain;

/**
 *
 * @author jdlopez
 */
public class AutoInstallResult {

    private boolean success;
    private String message;

    public AutoInstallResult() {}

    public AutoInstallResult(boolean ok, String message) {
        this.success = ok;
        this.message = message;
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
}
