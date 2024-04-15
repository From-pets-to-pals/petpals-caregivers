package com.petpals.caregivers.application.entrypoints;

import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.domain.pojo.Caregivers;
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

    public SaveCaregiverResource(CaregiversServicePort caregiversServicePort) {
        this.caregiversServicePort = caregiversServicePort;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addCaregiver(CreateCaregiver careviger) {
        LOG.info(String.format("Received request to add caregiver: %s", careviger));
        Caregivers caregivers = new Caregivers(
                null,
                careviger.firstName(),
                careviger.lastName(),
                careviger.email(),
                careviger.phoneNumber(),
                careviger.address(),
                careviger.city(),
                careviger.zipCode(),
                careviger.country(),
                careviger.workingDays(),
                careviger.palsHandled(),
                careviger.homeService(),
                careviger.appointmentDuration(),
                careviger.priceRating(),
                careviger.serviceRating(),
                careviger.isSubscribed(),
                null,
                careviger.caregiverType()
                );
        return caregiversServicePort.addCaregiver(caregivers);
    }
}
