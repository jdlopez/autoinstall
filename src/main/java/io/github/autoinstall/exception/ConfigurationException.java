package io.github.autoinstall.exception;

/**
 * Raises when some config was missing or incorrect. Extends Runtime so no need to declaration, indeed is a runtime problem
 */
public class ConfigurationException extends RuntimeException {

    public ConfigurationException(String message) {
        super(message);
    }
}
