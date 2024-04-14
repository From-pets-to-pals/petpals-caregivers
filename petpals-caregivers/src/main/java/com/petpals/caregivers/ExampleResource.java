package com.petpals.caregivers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Path("/hello")
public class ExampleResource {
    @HeaderParam("API-KEY")
    @Parameter(name = "API-KEY")
    String apiKey;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("/withName")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloName(String name) {
        return "Hello " + name;
    }
}
