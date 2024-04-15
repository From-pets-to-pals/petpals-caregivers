package com.petpals.caregivers.application.entrypoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

@Path("/hello")
@SecurityRequirement(name = "api_key")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloName(@PathParam("name")  String name) {
        return "Hello " + name;
    }
}
