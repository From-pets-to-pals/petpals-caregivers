package com.petpals.caregivers.persistence.services;

import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.caregivers.persistence.entities.Vets;

public interface CaregiversPersistencePort {
    void addGroomer(Groomers groomers);
    void addVet(Vets groomers);
    void addTrainer(Trainers groomers);

}
