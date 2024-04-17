package com.petpals.caregivers.bootstrap;

import com.petpals.caregivers.persistence.errorhandling.DBPersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<DBPersistenceException> {
    @Override
    public Response toResponse(DBPersistenceException e) {
        return Response.status(Response.Status.fromStatusCode(e.getStatusCode())).entity(e.getMessage()).build();
    }
}