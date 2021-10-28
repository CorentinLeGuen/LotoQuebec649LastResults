package com.redmangoose.apps.entity.exception;

public class LotoQuebecBadBindingException extends Exception {
    public LotoQuebecBadBindingException() {
        super("Unable to bind the value received");
    }

    public LotoQuebecBadBindingException(String message) {
        super(message);
    }
}
