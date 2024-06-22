package com.petpals.caregivers.application.entrypoints;

import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.application.mappers.CaregiversMapper;
import com.petpals.caregivers.persistence.services.CaregiversPersistencePort;
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
    private static final Logger LOG = Logger.getLogger(SaveCaregiverResource.class);

    CaregiversPersistencePort caregiversPersistencePort;
    CaregiversMapper caregiversMapper;
    public SaveCaregiverResource(CaregiversPersistencePort caregiversPersistencePort, CaregiversMapper caregiversMapper) {
        this.caregiversPersistencePort = caregiversPersistencePort;
        this.caregiversMapper = caregiversMapper;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public void addCaregiver(CreateCaregiver caregiver) {
        LOG.info(String.format("Received request to add caregiver: %s", caregiver));
        switch (caregiver.caregiverType()){
            case VET -> {
                var vet = caregiversMapper.toVet(caregiver);
                caregiversPersistencePort.addVet(vet);
            }
            case GROOMER -> {
                var groomer = caregiversMapper.toGroomer(caregiver);
                caregiversPersistencePort.addGroomer(groomer);
            }
            case TRAINER -> {
                var trainer = caregiversMapper.toTrainer(caregiver);
                caregiversPersistencePort.addTrainer(trainer);
            }
        }
    }
}
