package com.petpals.caregivers.bootstrap.interceptors;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;


@Provider
public class InboundInterceptor implements ContainerRequestFilter {
    @ConfigProperty(name = "api.key")
    String apiKey;
    @Context
    UriInfo uriInfo;
    @Override
    public void filter(ContainerRequestContext context) {
        if(uriInfo.getPath().startsWith("/hello")){
            return;
        }
        if (context.getHeaderString("API-KEY") == null || !context.getHeaderString("API-KEY").equals(apiKey)) {
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
