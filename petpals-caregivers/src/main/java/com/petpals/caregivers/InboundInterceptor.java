package com.petpals.caregivers;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@Provider
public class InboundInterceptor implements ContainerRequestFilter {
    @ConfigProperty(name = "api.key")
    String apiKey;
    @Override
    public void filter(ContainerRequestContext context) {
        if (context.getHeaderString("API-KEY") == null || !context.getHeaderString("API-KEY").equals(apiKey)) {
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
