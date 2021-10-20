package com.redmangoose.apps.entity.errors;

import com.redmangoose.apps.entity.LotoQuebecObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LotoQuebecWebRequestError implements LotoQuebecObject {
    private final String status;
    private final String error;
    private final String timestamp;

    public LotoQuebecWebRequestError() {
        this.status = "500";
        this.error = "Unable to contact the server";
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
