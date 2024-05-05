package com.petpals.caregivers.bootstrap.interceptors;

import io.quarkus.qute.Template;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    Template error404;

    @Inject
    public NotFoundExceptionMapper(Template error404) {
        this.error404 = error404;
    }

    @Override
    @Produces(MediaType.TEXT_HTML)
    public Response toResponse(NotFoundException exception) {
        return Response.ok(error404.render()).build();
    }
}
