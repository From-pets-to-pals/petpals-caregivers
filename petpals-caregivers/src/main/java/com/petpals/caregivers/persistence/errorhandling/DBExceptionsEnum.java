package com.petpals.caregivers.persistence.errorhandling;

import org.apache.http.HttpStatus;

public enum DBExceptionsEnum {
    DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION(
            "User already exists.",
            HttpStatus.SC_NOT_ACCEPTABLE);

    private final String message;

    private final int httpResponseStatus;

    DBExceptionsEnum(String userMessageToDisplay
            , int httpResponseStatus) {
        this.message = userMessageToDisplay;
        this.httpResponseStatus = httpResponseStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpResponseStatus() {
        return httpResponseStatus;
    }
}
