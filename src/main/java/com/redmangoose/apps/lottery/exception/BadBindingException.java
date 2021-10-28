package com.redmangoose.apps.lottery.exception;

/**
 * This exception is throw when we are unable to bind received remote results with the API.
 * This may occur if the remote server change their objects format.
 */
public class BadBindingException extends Exception {
    public BadBindingException() {
        super("Unable to bind the value received");
    }

    public BadBindingException(String message) {
        super(message);
    }
}
