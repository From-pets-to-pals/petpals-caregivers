package com.petpals.caregivers.bootstrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.caregivers.persistence.errorhandling.DBPersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class ExceptionHandler implements ExceptionMapper<DBPersistenceException> {
    @Override
    public Response toResponse(DBPersistenceException e) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Logger.getLogger("here").info(objectMapper.writeValueAsString(e));
            return Response.status(Response.Status.fromStatusCode(e.getAppErrorCode().getHttpResponseStatus())).entity(objectMapper.writeValueAsString(e.getAppErrorCode())).build();
        } catch (JsonProcessingException ex) {
            return Response.status(Response.Status.fromStatusCode(e.getAppErrorCode().getHttpResponseStatus())).entity(e.getAppErrorCode().getMessage()).build();

        }
    }
}