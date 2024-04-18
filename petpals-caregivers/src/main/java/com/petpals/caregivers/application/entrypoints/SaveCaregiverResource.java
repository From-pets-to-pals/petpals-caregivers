package com.petpals.caregivers.application.entrypoints;

import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.application.mappers.CreateCaregiverMapper;
import com.petpals.caregivers.domain.ports.in.CaregiversServicePort;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.jboss.logging.Logger;

@Path("/caregivers")
@SecurityRequirement(name = "api_key")
public class SaveCaregiverResource {
    private final Logger LOG = Logger.getLogger(SaveCaregiverResource.class);

    CaregiversServicePort caregiversServicePort;
    CreateCaregiverMapper createCaregiverMapper;
    public SaveCaregiverResource(CaregiversServicePort caregiversServicePort, CreateCaregiverMapper createCaregiverMapper) {
        this.caregiversServicePort = caregiversServicePort;
        this.createCaregiverMapper = createCaregiverMapper;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addCaregiver(CreateCaregiver caregiver) {
        LOG.info(String.format("Received request to add caregiver: %s", caregiver));
        return caregiversServicePort.addCaregiver(createCaregiverMapper.toDomain(caregiver));
    }
}
