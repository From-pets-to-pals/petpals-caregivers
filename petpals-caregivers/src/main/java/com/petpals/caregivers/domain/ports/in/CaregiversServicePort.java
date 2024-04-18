package com.petpals.caregivers.domain.ports.in;

import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;

public interface CaregiversServicePort {
    String addCaregiver(CreateCaregiverCommand caregiver);
}
