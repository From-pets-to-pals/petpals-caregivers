package com.petpals.caregivers.persistence.errorhandling;

public class DBPersistenceException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private int statusCode;

    public DBPersistenceException(DBExceptionsEnum exception) {
        super(exception.getMessage());
        this.statusCode = exception.getHttpResponseStatus();
    }
}
