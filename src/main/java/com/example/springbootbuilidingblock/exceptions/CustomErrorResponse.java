package com.example.springbootbuilidingblock.exceptions;

public class CustomErrorResponse {
    private String timestamp;
    private String message;
    private String errorDetails;

    public CustomErrorResponse(String timestamp, String message, String errorDetails) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
