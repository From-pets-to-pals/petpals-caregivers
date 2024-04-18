package com.petpals.caregivers.persistence.mappers;

import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.caregivers.persistence.entities.Vets;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CaregiversMapper {
    Groomers toGroomer(CreateCaregiverCommand caregiver);
    Vets toVet(CreateCaregiverCommand caregiver);
    Trainers toTrainer(CreateCaregiverCommand caregiver);

}
