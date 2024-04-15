package com.petpals.caregivers.domain.ports.out;

import com.petpals.caregivers.domain.pojo.Caregivers;

public interface CaregiversPersistencePort {
    void addGroomer(Caregivers caregiver);
    void addTrainer(Caregivers caregiver);
    void addVet(Caregivers caregiver);

}
