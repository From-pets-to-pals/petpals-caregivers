package com.petpals.caregivers.bootstrap.interceptors;

import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@Provider
public class InboundInterceptor implements ContainerRequestFilter {
    @ConfigProperty(name = "api.key")
    String apiKey;
    @Context
    UriInfo uriInfo;
    @Override
    public void filter(ContainerRequestContext context) {
        if (context.getHeaderString("API-KEY") == null || !context.getHeaderString("API-KEY").equals(apiKey)) {
            if(uriInfo.getPath().equals("/hello")){
                return;
            }
            throw new PetPalsExceptions(ExceptionsEnum.CAREGIVERS_MISSING_API_KEY);
        }
    }
}
