package com.petpals.caregivers.persistence.errorhandling;

public class DBPersistenceException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final DBExceptionsEnum appErrorCode;

    public DBPersistenceException(DBExceptionsEnum exception) {
        this.appErrorCode = exception;
    }

    public DBExceptionsEnum getAppErrorCode() {
        return appErrorCode;
    }

    @Override
    public String toString() {
        return "DBPersistenceException{" +
                ", appErrorCode=" + appErrorCode +
                '}';
    }
}
