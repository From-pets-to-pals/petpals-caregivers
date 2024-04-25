package com.petpals.caregivers.bootstrap.interceptors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class ExceptionHandler implements ExceptionMapper<PetPalsExceptions> {
    @Override
    public Response toResponse(PetPalsExceptions e) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Logger.getLogger("here").info(objectMapper.writeValueAsString(e));
            return Response.status(Response.Status.fromStatusCode(e.getAppError().getHttpResponseStatus())).entity(objectMapper.writeValueAsString(e.getAppError())).build();
        } catch (JsonProcessingException ex) {
            return Response.status(Response.Status.fromStatusCode(e.getAppError().getHttpResponseStatus())).entity(e.getAppError().getMessage()).build();

        }
    }
}