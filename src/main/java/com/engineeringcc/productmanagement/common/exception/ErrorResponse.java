package com.engineeringcc.productmanagement.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {

    private String message;
    private String exception;
    private String path;
    private Map<String, String> errors;

    public ErrorResponse(String message, String exception, String path, Map<String, String> errors) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.errors = errors;
    }

    public ErrorResponse(String message, String exception, String path) {
        this.message = message;
        this.exception = exception;
        this.path = path;
        this.errors = new HashMap<>();
    }

    public String getMessage() {
        return message;
    }

    public String getException() {
        return exception;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
