package com.redmangoose.apps.entity;

import java.util.ArrayList;
import java.util.List;

public class LotoQuebecWebRequestError implements LotoQuebecObject {
    private final String status;
    private final List<String> errors;

    public LotoQuebecWebRequestError() {
        this.status = "500";
        this.errors = new ArrayList<>();
        this.errors.add("Unable to contact the server.");
    }

    public String getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }
}
