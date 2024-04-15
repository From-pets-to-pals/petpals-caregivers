package com.petpals.caregivers.domain.ports.out;

import com.petpals.caregivers.domain.Caregivers;

public interface GroomersPersistence {
    void saveGroomer(Caregivers caregiver);
}
