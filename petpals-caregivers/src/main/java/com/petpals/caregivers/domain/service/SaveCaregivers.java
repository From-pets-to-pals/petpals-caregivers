package com.petpals.caregivers.domain.service;

import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import com.petpals.caregivers.domain.ports.in.CaregiversServicePort;
import com.petpals.caregivers.domain.ports.out.CaregiversPersistencePort;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.errorhandling.ApplicationExceptions;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.jboss.logging.Logger;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class SaveCaregivers implements CaregiversServicePort {
    private static final Logger LOGGER = Logger.getLogger(SaveCaregivers.class);
    CaregiversPersistencePort caregiversPersistencePort;

    Validator validator;

    public SaveCaregivers(CaregiversPersistencePort caregiversPersistencePort, Validator validator) {
        this.caregiversPersistencePort = caregiversPersistencePort;
        this.validator = validator;
    }

    public String addCaregiver(CreateCaregiverCommand caregiver) {
        String caregiverReference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,"");
        if(caregiver.getReference() == null){
            caregiver.setReference(caregiverReference);
        }
        Set<ConstraintViolation<CreateCaregiverCommand>> violations = validator.validate(caregiver);
        if (violations.isEmpty()) {
            caregiver.setSubscribed(false);
            caregiver.setPriceRating(5.0);
            caregiver.setServiceRating(5.0);
            switch (caregiver.getCaregiverType()){
                case GROOMER:
                        caregiversPersistencePort.addGroomer(caregiver);
                    break;
                case VET:
                        caregiversPersistencePort.addVet(caregiver);
                    break;
                case TRAINER:
                        caregiversPersistencePort.addTrainer(caregiver);
                    break;
            }
            return caregiverReference;
        } else {
            LOGGER.info(violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet()).toString());
            throw new ApplicationExceptions(ExceptionsEnum.CREATE_CAREGIVER_SERVICE_INDALID_COMMAND);
        }
    }
}
