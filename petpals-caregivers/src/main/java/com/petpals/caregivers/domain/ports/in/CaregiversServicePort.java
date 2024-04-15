package com.petpals.caregivers.domain.ports.in;

import com.petpals.caregivers.domain.pojo.Caregivers;

public interface CaregiversServicePort {
    String addCaregiver(Caregivers caregiver);
}
