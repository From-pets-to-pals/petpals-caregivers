package com.petpals.caregivers.persistence.entities.services;

import com.petpals.caregivers.persistence.entities.Caregivers;
import com.petpals.caregivers.persistence.entities.CaregiversRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaregiversPersistence {
    private final CaregiversRepository caregiversRepository;

    public CaregiversPersistence(CaregiversRepository caregiversRepository) {
        this.caregiversRepository = caregiversRepository;
    }

    public void saveCaregiver(Caregivers caregiver) {
        caregiversRepository.persist(caregiver);
        caregiversRepository.flush();
    }
}
