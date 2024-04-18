package com.petpals.caregivers.domain.ports.out;


import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;

public interface CaregiversPersistencePort {
    void addGroomer(CreateCaregiverCommand caregiver);
    void addTrainer(CreateCaregiverCommand caregiver);
    void addVet(CreateCaregiverCommand caregiver);

}
