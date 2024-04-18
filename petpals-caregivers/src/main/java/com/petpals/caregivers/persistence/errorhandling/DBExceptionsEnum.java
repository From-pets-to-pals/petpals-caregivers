package com.petpals.caregivers.persistence.errorhandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.http.HttpStatus;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DBExceptionsEnum {
    DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION(
            "User already exists.",
            HttpStatus.SC_UNAUTHORIZED,
            1);

    private final String message;

    private final int httpResponseStatus;
    private final int appErrorCode;

    DBExceptionsEnum(String userMessageToDisplay
            , int httpResponseStatus, int appErrorCode) {
        this.message = userMessageToDisplay;
        this.httpResponseStatus = httpResponseStatus;
        this.appErrorCode = appErrorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpResponseStatus() {
        return httpResponseStatus;
    }

    @Override
    public String toString() {
        return "DBExceptionsEnum{" +
                "message='" + message + '\'' +
                ", httpResponseStatus=" + httpResponseStatus +
                ", appErrorCode=" + appErrorCode +
                '}';
    }
}
