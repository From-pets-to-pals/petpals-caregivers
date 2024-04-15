package com.petpals.caregivers.domain.service;

import com.petpals.caregivers.domain.pojo.Caregivers;
import com.petpals.caregivers.domain.ports.out.CaregiversPersistencePort;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;

@ApplicationScoped
public class SaveCaregivers {
    CaregiversPersistencePort caregiversPersistencePort;

    Validator validator;

    public SaveCaregivers(CaregiversPersistencePort caregiversPersistencePort, Validator validator) {
        this.caregiversPersistencePort = caregiversPersistencePort;
        this.validator = validator;
    }

    public String addCaregiver(Caregivers caregiver) {
        String caregiverReference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,"");
        if(caregiver.getReference() == null){
            caregiver.setReference(caregiverReference);
        }
        Set<ConstraintViolation<Caregivers>> violations = validator.validate(caregiver);
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
            throw new RuntimeException();
        }
    }
}
