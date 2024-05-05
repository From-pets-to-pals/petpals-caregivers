package com.petpals.caregivers.bootstrap.interceptors;

import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<PetPalsExceptions> {
    @Override
    public Response toResponse(PetPalsExceptions e) {
        return Response.status(Response.Status.fromStatusCode(e.getAppError().getHttpResponseStatus())).entity(e.getAppError().getMessage()).build();
    }
}